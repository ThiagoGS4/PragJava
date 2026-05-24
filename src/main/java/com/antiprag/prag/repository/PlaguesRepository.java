package com.antiprag.prag.repository;

import com.antiprag.prag.domain.Plagues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaguesRepository extends JpaRepository<Plagues, Integer> {
    
    Plagues findByName(String name);
//  @Override
//  java.util.Optional<Usuario> findById(Integer id);
}
