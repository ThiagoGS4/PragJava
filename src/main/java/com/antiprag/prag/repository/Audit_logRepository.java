package com.antiprag.prag.repository;

import com.antiprag.prag.domain.Audit_log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Audit_logRepository extends JpaRepository<Audit_log, Integer> {
    
    // Audit_log findByName(String name); --> isso tava causando erro
//  @Override
//  java.util.Optional<Usuario> findById(Integer id);
}