package com.antiprag.prag.repository;

import com.antiprag.prag.domain.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Integer> {
    
    // Audit_log findByName(String name); --> isso tava causando erro
//  @Override
//  java.util.Optional<Usuario> findById(Integer id);
}