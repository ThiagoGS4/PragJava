package com.antiprag.prag.repository;

import com.antiprag.prag.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    
    Admin findByNome(String nome);
//  @Override
//  java.util.Optional<Usuario> findById(Integer id);
}