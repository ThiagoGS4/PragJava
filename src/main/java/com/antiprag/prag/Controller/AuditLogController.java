package com.antiprag.prag.Controller;
import com.antiprag.prag.domain.AuditLog;
import com.antiprag.prag.service.AuditLogService;
import java.io.IOException;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class AuditLogController {

    private AuditLogService audit_logService;

    public AuditLogController(AuditLogService audit_logService) {
        this.audit_logService = audit_logService;
    }

    @GetMapping(path = "/auditLog")
    public List<AuditLog> ListAudit_log() {
        return audit_logService.ListAudit_log();
    }

    @PostMapping(path = "/inserirAuditLog")
    public void inserirAudit_log(@RequestBody AuditLog audit_log) throws IOException {
        audit_logService.inserirAudit_log(audit_log);
    }
}