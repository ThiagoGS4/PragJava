package com.antiprag.prag.mapper;

import java.time.Instant;

import org.springframework.stereotype.Component;

import com.antiprag.prag.domain.RefreshToken;
import com.antiprag.prag.domain.Users;


@Component
public class RefreshTokenMapper {

    public RefreshToken toEntity(Users user, String token, Instant expiresAt, String jti){
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setJti(jti);
        refreshToken.setUsers(user);
        refreshToken.setToken(token);
        refreshToken.setExpiresAt(expiresAt);

        return refreshToken;
    }
}
