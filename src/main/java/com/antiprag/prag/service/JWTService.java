package com.antiprag.prag.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.antiprag.prag.DTO.TokensDTO;
import com.antiprag.prag.domain.RefreshToken;
import com.antiprag.prag.domain.Roles;
import com.antiprag.prag.domain.Users;
import com.antiprag.prag.mapper.RefreshTokenMapper;
import com.antiprag.prag.mapper.TokensMapper;
import com.antiprag.prag.repository.RefreshTokenRepository;
import com.antiprag.prag.repository.UsersRepository;

import javax.crypto.SecretKey;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@ConfigurationProperties(prefix = "app.jwt-zone")
@Getter
@Setter
@RequiredArgsConstructor
public class JWTService {

    String secretkey;
    Long accessExpirationMs;
    Long refreshExpirationMs;
    Long zoneTime;

    private final RefreshTokenMapper refreshTokenMapper;

    private final UsersRepository usersRepository;

    private final RefreshTokenRepository refreshTokenRepository;

    private final TokensMapper tokensMapper;

/*     public JWTService(String secret) {
        secretkey = secret;
    } */
   
   private SecretKey getKey() {
       byte[] keyBytes = Decoders.BASE64.decode(secretkey);
       return Keys.hmacShaKeyFor(keyBytes);
    }
    
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    
    public String extractJti(String token) {
        return extractClaim(token, Claims::getId);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }
    
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
        .verifyWith(getKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();
    }
    
    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

public boolean validateRefreshToken(String token) {
    Claims claims = extractAllClaims(token);

    String jti = claims.getId();
    String username = claims.getSubject();

    Users user = usersRepository.findByUsername(username);

    RefreshToken rt = refreshTokenRepository
            .findByJtiAndUsersIdAndRevokedFalse(jti, user.getId())
            .orElseThrow(() -> new RuntimeException("Refresh token inválido"));

    return rt.getExpiresAt().isAfter(Instant.now());
}

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public TokensDTO generateTokens(String username, Collection<Roles> roles) {
        Users extractedUser = usersRepository.findByUsername(username);

        Set<String> rolesToStringSet = extractedUser.getRoles()
            .stream()
            .map(Roles::getName)
            .collect(Collectors.toSet());

        String refreshToken = generateRefreshToken(username, rolesToStringSet, extractedUser);
        // gerando access token, roles são convertidos para role_id, se tentar mandar uma lista de roles causa erro...
        String accesstoken = buildToken(username, rolesToStringSet, accessExpirationMs);

        TokensDTO tokens = tokensMapper.tokensOut(accesstoken, refreshToken);

        return tokens;
    }

    public String generateRefreshToken(String username, Set<String> roles, Users extractedUser) {
        // convertendo data de expiração para Instant que é o melhor formato para salvar dados (não causa bugs de internacionalização)
        Instant expiresAt = Instant.now().plusMillis(refreshExpirationMs).minusSeconds(zoneTime);

        String jti = UUID.randomUUID().toString(); // gerando id de token aleatório
        
        String token = buildToken(username, roles, refreshExpirationMs, jti); // transformar roles em String array

        RefreshToken refreshToken = refreshTokenMapper.toEntity(extractedUser, token, expiresAt, jti);

        refreshTokenRepository.save(refreshToken);
        return token;
    }

    public String buildToken(String nome, Set<String> roles, Long expiration) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
        .claims()
        .add(claims)
        .subject(nome)
        .add("roles", roles)
                .and()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getKey())
                .compact();
    }

    public String buildToken(String nome, Set<String> roles, Long expiration, String jti) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
        .id(jti)
        .claims()
        .add(claims)
        .subject(nome)
        .add("roles", roles)
                .and()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getKey())
                .compact();
    }
            
}