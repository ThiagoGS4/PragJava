package com.antiprag.prag.mapper;

import org.springframework.stereotype.Component;
import com.antiprag.prag.DTO.AuditLogOutDTO;
import com.antiprag.prag.domain.AuditLog;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuditLogMapper {
    public AuditLogOutDTO auditLogToOut(AuditLog auditLog){
        String username = (auditLog.getUsers() != null) 
            ? auditLog.getUsers().getUsername() 
            : "Não autenticado";

        return new AuditLogOutDTO(
            auditLog.getId(),
            auditLog.getOperation(),
            auditLog.getMethod(),
            username,
            auditLog.getCreated_at(),
            auditLog.getStatus(),
            auditLog.getIp(),
            auditLog.getPayload()
        );
    }
}
