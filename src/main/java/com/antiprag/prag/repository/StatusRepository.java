package com.antiprag.prag.repository;

import com.antiprag.prag.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {
    
    Status findByName(String name);
//  @Override
//  java.util.Optional<Usuario> findById(Integer id);
}
