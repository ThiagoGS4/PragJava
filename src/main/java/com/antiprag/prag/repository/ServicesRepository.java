package com.antiprag.prag.repository;

import com.antiprag.prag.domain.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicesRepository extends JpaRepository<Services, Integer> {
    
    Services findByName(String name);
//  @Override
//  java.util.Optional<Usuario> findById(Integer id);
}
