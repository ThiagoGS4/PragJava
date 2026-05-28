package com.antiprag.prag.repository;

import com.antiprag.prag.domain.Customer_status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Customer_statusRepository extends JpaRepository<Customer_status, Integer> {
    
//  @Override
//  java.util.Optional<Usuario> findById(Integer id);
}