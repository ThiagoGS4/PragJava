package com.antiprag.prag.repository;

import com.antiprag.prag.domain.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<History, Integer> {
    
    History findByName(String name);
//  @Override
//  java.util.Optional<Usuario> findById(Integer id);
}
