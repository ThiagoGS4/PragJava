package com.antiprag.prag.service;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.antiprag.prag.DTO.SchedulesInDTO;
import com.antiprag.prag.DTO.SchedulesOutDTO;
import com.antiprag.prag.domain.Schedules;
import com.antiprag.prag.domain.UsuarioPrincipal;
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

    public SchedulesOutDTO alterarSchedules(SchedulesInDTO schedules, UsuarioPrincipal usuarioPrincipal) {
        Integer userId = usuarioPrincipal.getId();

        Schedules schedulesEntity = schedulesRepository.findById(schedules.id())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
        
        schedulesEntity = schedulesMapper.updateSchedulesToEntity(schedules, schedulesEntity, userId);
        return schedulesMapper.schedulesToOutDto(schedulesRepository.save(schedulesEntity));
    }
    
    public SchedulesOutDTO inserirSchedules(SchedulesInDTO schedules, UsuarioPrincipal usuarioPrincipal) {
        Integer userId = usuarioPrincipal.getId();

        Schedules schedulesEntity = schedulesMapper.schedulesToEntity(schedules, userId);
        return schedulesMapper.schedulesToOutDto(schedulesRepository.save(schedulesEntity));
    }

}