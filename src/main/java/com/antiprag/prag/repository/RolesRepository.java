package com.antiprag.prag.repository;

import com.antiprag.prag.domain.Roles;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {
    Optional<Roles> findByName(Roles name);
}
