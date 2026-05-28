package com.antiprag.prag.repository;

import com.antiprag.prag.domain.Properties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertiesRepository extends JpaRepository<Properties, Integer> {
    
//  @Override
//  java.util.Optional<Usuario> findById(Integer id);
}
