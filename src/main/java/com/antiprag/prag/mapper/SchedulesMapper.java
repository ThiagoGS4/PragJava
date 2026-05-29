package com.antiprag.prag.mapper;

import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.antiprag.prag.DTO.SchedulesInDTO;
import com.antiprag.prag.DTO.SchedulesOutDTO;
import com.antiprag.prag.domain.Schedules;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Component
public class SchedulesMapper {
    
    @PersistenceContext
    private EntityManager entityManager;

    // dto para output de dados do(s) clientes(s)
    public SchedulesOutDTO schedulesToOutDto(Schedules schedules) {
        return new SchedulesOutDTO(
                schedules.getScheduled_start(),
                schedules.getScheduled_end(),
                schedules.getScheduled_at(),
                schedules.getNotes(),
                schedules.getCreated_at(),
                schedules.getDeleted_at(),
                schedules.getUpdated_at()/* ,
                properties
                plagues,
                services,
                status,
                user*/
                
        );
    }

    // dto para input de cliente
    public Schedules schedulesToEntity(SchedulesInDTO dto) {
        Schedules schedules = new Schedules();
        schedules.setScheduled_start(dto.scheduled_start());
        schedules.setScheduled_end(dto.scheduled_end());
        schedules.setScheduled_at(dto.scheduled_at());
        schedules.setNotes(dto.notes());/*
        schedules.setProperties(properties);
        schedules.setPlagues(plagues);
        schedules.setServices(service);
        schedules.setStatus(status);
        schedules.setUser(user);
        */
        return schedules;
    }
}


