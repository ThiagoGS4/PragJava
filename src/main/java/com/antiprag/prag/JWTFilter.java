package com.antiprag.prag;

import com.antiprag.prag.domain.AuditLog;
import com.antiprag.prag.domain.Users;
import com.antiprag.prag.handler.JWTErrorHandler;
import com.antiprag.prag.repository.UsersRepository;
import com.antiprag.prag.service.AuditLogService;
import com.antiprag.prag.service.JWTService;
import com.antiprag.prag.service.UsuarioDetailService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "app.dev")
public class JWTFilter extends OncePerRequestFilter {

    private Boolean development;

    private final AuditLogService auditLogService;
    private final UsersRepository usersRepository;

    public void setDevelopment(Boolean development) {
        this.development = development;
    }

    private final JWTService jwtService;

    private final ApplicationContext context;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String nome = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            try{
                nome = jwtService.extractUserName(token); // pegando username através de claims do JWT
            } catch (ExpiredJwtException error){
                JWTErrorHandler.handleValidation(response, "Access Token expirado.", "TOKEN_EXPIRED", error.getMessage());
                return;
            }
        }

        // vendo se usuário é nulo e se já existe sessão ativa desse usuário
        // TODO esse check de token/usuário está muito estranho, ele revalida nome de
        // usuário 2 vezes...
        if (nome != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = context.getBean(UsuarioDetailService.class).loadUserByUsername(nome);
            if (jwtService.validateToken(token, userDetails)) {
                // logando com token validado, agora usando para a autenticar
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken); // token salvo no security context, ele
                                                                                 // salva essa sessão como nova
            }
        }

        filterChain.doFilter(request, response);
        // fazendo audit log no retorno da operação
        String method = request.getMethod();
        String uri = request.getRequestURI();
        Integer status = response.getStatus();

        AuditLog auditLog = new AuditLog();
        auditLog.setStatus(status);
        auditLog.setMethod(method);
        auditLog.setOperation(uri);

        Integer userId = null;
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null
                && authentication.isAuthenticated()
                && authentication.getPrincipal() instanceof UserDetails userDetails) {
            Users user = context.getBean(UsuarioDetailService.class)
                    .findUserByUsername(userDetails.getUsername());
            userId = user.getId();
        }

       Users userFound = usersRepository.findById(userId)
            .orElseThrow(() -> new Error("User not found"));;

        auditLog.setUsers(userFound);

        auditLogService.inserirAudit_log(auditLog);
    }

    private final List<String> excludedMatchers = List.of(
            ("/logar"),
            ("/registrar"),
            ("/refreshLogin")
    );

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return Boolean.TRUE.equals(development) || excludedMatchers.contains(request.getServletPath());
    }
}