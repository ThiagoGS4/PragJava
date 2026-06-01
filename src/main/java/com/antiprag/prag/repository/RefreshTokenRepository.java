package com.antiprag.prag.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.antiprag.prag.domain.RefreshToken;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
    Optional<RefreshToken> findByJtiAndRevokedFalse(String jti);

    Optional<RefreshToken> findByJtiAndUsersIdAndRevokedFalse(String jti, Integer userId);
}
