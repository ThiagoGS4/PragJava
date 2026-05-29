package com.antiprag.prag.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.antiprag.prag.DTO.SchedulesInDTO;
import com.antiprag.prag.DTO.SchedulesOutDTO;
import com.antiprag.prag.domain.Schedules;
import com.antiprag.prag.mapper.SchedulesMapper;
import com.antiprag.prag.repository.SchedulesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SchedulesService {

    private final SchedulesRepository schedulesRepository;

    private final SchedulesMapper schedulesMapper;

    public SchedulesOutDTO getSchedules(Integer id) {
        Schedules schedules = schedulesRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Schedules not found"));

        return schedulesMapper.schedulesToOutDto(schedules);
    }

    public List<SchedulesOutDTO> ListSchedules() {
        return schedulesRepository.findAll()
            .stream()
            .map(schedulesMapper::schedulesToOutDto)
            .toList();
    }

    public void deletarSchedules(int idSchedules) {
        schedulesRepository.deleteById(idSchedules);
    }

    public void alterarSchedules(Schedules Schedules) {
        schedulesRepository.save(Schedules);
    }
    
    public SchedulesInDTO inserirSchedules(SchedulesInDTO schedules) {
        return schedules;
    }

}