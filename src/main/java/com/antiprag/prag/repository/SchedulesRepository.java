package com.antiprag.prag.repository;

import com.antiprag.prag.domain.Schedules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulesRepository extends JpaRepository<Schedules, Integer> {
    

//  @Override
//  java.util.Optional<Usuario> findById(Integer id);
}
