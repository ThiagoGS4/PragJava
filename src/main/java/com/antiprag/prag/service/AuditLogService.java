package com.antiprag.prag.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.antiprag.prag.domain.AuditLog;
import com.antiprag.prag.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuditLogService {

    private final AuditLogRepository audit_logRepository;

    public List<AuditLog> ListAudit_log() {
        return audit_logRepository.findAll();
    }

    public void inserirAudit_log(AuditLog audit_log) {
        audit_logRepository.save(audit_log);
    }
}