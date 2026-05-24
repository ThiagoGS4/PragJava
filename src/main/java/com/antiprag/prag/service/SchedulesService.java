package com.antiprag.prag.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import com.antiprag.prag.domain.Schedules;
import com.antiprag.prag.repository.SchedulesRepository;

@Service
public class SchedulesService {

    @Autowired
    private SchedulesRepository schedulesRepository;

    @Autowired
    AuthenticationManager authManager;

    public SchedulesService(SchedulesRepository schedulesRepository) {
        this.schedulesRepository = schedulesRepository;
    }

    public Schedules getSchedules(Integer id) {
        return schedulesRepository.findById(id).orElse(null);
    }

    public List<Schedules> ListSchedules() {
        return schedulesRepository.findAll();
    }

    public void deletarSchedules(int idSchedules) {
        schedulesRepository.deleteById(idSchedules);
    }

    public void alterarSchedules(Schedules Schedules) {
        schedulesRepository.save(Schedules);
    }

    public void inserirSchedules(Schedules schedules) {
        schedulesRepository.save(schedules);
    }
}