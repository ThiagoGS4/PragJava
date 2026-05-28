package com.antiprag.prag.repository;

import com.antiprag.prag.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    
    Users findByUsername(String username);
//  @Override
//  java.util.Optional<Usuario> findById(Integer id);
}