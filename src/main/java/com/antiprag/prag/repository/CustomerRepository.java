package com.antiprag.prag.repository;

import com.antiprag.prag.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    
//  @Override
//  java.util.Optional<Usuario> findById(Integer id);
}
