package com.antiprag.prag.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.antiprag.prag.domain.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Optional<Permission> findByName(String name);
}
