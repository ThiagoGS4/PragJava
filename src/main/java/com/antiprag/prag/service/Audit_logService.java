package com.antiprag.prag.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.antiprag.prag.domain.Audit_log;
import com.antiprag.prag.repository.Audit_logRepository;

@Service
public class Audit_logService {

    @Autowired
    private Audit_logRepository audit_logRepository;


    public Audit_logService(Audit_logRepository audit_logRepository) {
        this.audit_logRepository = audit_logRepository;
    }

    public Audit_log getAudit_log(Integer id) {
        return audit_logRepository.findById(id).orElse(null);
    }

    public List<Audit_log> ListAudit_log() {
        return audit_logRepository.findAll();
    }

    public void deletarAudit_log(int idAudit_log) {
        audit_logRepository.deleteById(idAudit_log);
    }

    public void alterarAudit_log(Audit_log Audit_log) {
        audit_logRepository.save(Audit_log);
    }

    public void inserirAudit_log(Audit_log audit_log) {
        audit_logRepository.save(audit_log);
    }
}