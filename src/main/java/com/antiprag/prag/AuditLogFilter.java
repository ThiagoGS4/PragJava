package com.antiprag.prag;

import java.io.IOException;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import com.antiprag.prag.domain.AuditLog;
import com.antiprag.prag.domain.Users;
import com.antiprag.prag.repository.UsersRepository;
import com.antiprag.prag.service.AuditLogService;
import com.antiprag.prag.service.UsuarioDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@Order(1)
@RequiredArgsConstructor
public class AuditLogFilter extends OncePerRequestFilter {

    private final ApplicationContext context;
    private final UsersRepository usersRepository;
    private final AuditLogService auditLogService;

    private final List<String> excludedMatchers = List.of(
            ("/logar"),
            ("/registrar"),
            ("/refreshLogin"));

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request, 0);
        try {
            filterChain.doFilter(wrappedRequest, response);
        } finally {
            // fazendo audit log no retorno da operação
            String method = request.getMethod();
            String uri = request.getRequestURI();
            Integer status = response.getStatus();
            String ip = request.getRemoteAddr();

            AuditLog auditLog = new AuditLog();
            auditLog.setStatus(status);
            auditLog.setMethod(method);
            auditLog.setOperation(uri);
            auditLog.setIp(ip);

            if (!excludedMatchers.contains(request.getRequestURI())) {
                byte[] buf = wrappedRequest.getContentAsByteArray();
                String payloadParsed = new String(buf, 0, buf.length, wrappedRequest.getCharacterEncoding());
                auditLog.setPayload(payloadParsed);
            }
            Integer userId = null;

            var authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication != null
                    && authentication.isAuthenticated()
                    && authentication.getPrincipal() instanceof UserDetails userDetails) {
                Users user = context.getBean(UsuarioDetailService.class)
                        .findUserByUsername(userDetails.getUsername());
                userId = user.getId();
            }

            if (userId != null) {
                usersRepository.findById(userId).ifPresent(auditLog::setUsers);
            }

            auditLogService.inserirAudit_log(auditLog);
        }

    }

    public void registrarErros(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // fazendo audit log no retorno da operação

            String method = request.getMethod();
            String uri = request.getRequestURI();
            Integer status = response.getStatus();
            String ip = request.getRemoteAddr();

            AuditLog auditLog = new AuditLog();
            auditLog.setStatus(status);
            auditLog.setMethod(method);
            auditLog.setOperation(uri);
            auditLog.setIp(ip);

            if (!excludedMatchers.contains(uri)) {
                String payloadParsed;

                if (request instanceof ContentCachingRequestWrapper ccr) {
                    byte[] buf = ccr.getContentAsByteArray();
                    payloadParsed = new String(buf, 0, buf.length, ccr.getCharacterEncoding());
                } else {
                    payloadParsed = new String(request.getInputStream().readAllBytes(),
                            request.getCharacterEncoding() != null ? request.getCharacterEncoding() : "UTF-8");
                }
                auditLog.setPayload(payloadParsed);
            }

            Integer userId = null;

            var authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication != null
                    && authentication.isAuthenticated()
                    && authentication.getPrincipal() instanceof UserDetails userDetails) {
                Users user = context.getBean(UsuarioDetailService.class)
                        .findUserByUsername(userDetails.getUsername());
                userId = user.getId();
            }

            if (userId != null) {
                usersRepository.findById(userId).ifPresent(auditLog::setUsers);
            }

            auditLogService.inserirAudit_log(auditLog);
    }
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath() == "/me";
    }
}
