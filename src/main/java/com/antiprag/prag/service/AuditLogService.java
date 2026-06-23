package com.antiprag.prag.service;

import com.antiprag.prag.mapper.AuditLogMapper;
import java.util.List;
import org.springframework.stereotype.Service;

import com.antiprag.prag.DTO.AuditLogOutDTO;
import com.antiprag.prag.domain.AuditLog;
import com.antiprag.prag.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuditLogService {

    private final AuditLogMapper auditLogMapper;
    private final AuditLogRepository audit_logRepository;

    public List<AuditLogOutDTO> ListAudit_log() {
        return audit_logRepository.findAll()
        .stream()
        .map(auditLogMapper::auditLogToOut)
        .toList();
    }

    public void inserirAudit_log(AuditLog audit_log) {
        audit_logRepository.save(audit_log);
    }
}