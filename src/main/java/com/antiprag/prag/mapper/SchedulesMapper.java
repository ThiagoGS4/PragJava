package com.antiprag.prag.mapper;

import java.time.Instant;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import com.antiprag.prag.DTO.SchedulesInDTO;
import com.antiprag.prag.DTO.SchedulesOutDTO;
import com.antiprag.prag.domain.Plagues;
import com.antiprag.prag.domain.Schedules;
import com.antiprag.prag.domain.Services;
import com.antiprag.prag.domain.Status;
import com.antiprag.prag.domain.Users;
import com.antiprag.prag.domain.Properties;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Component
@ConfigurationProperties(prefix = "app.zone")
public class SchedulesMapper {
    @PersistenceContext
    private EntityManager entityManager;

    private Long zoneTime;

    public void setZoneTime(Long zoneTime) {
        this.zoneTime = zoneTime;
    }

    // dto para output de dados do(s) clientes(s)
    public SchedulesOutDTO schedulesToOutDto(Schedules schedules) {

        return new SchedulesOutDTO(
                schedules.getScheduled_start(), 
                schedules.getScheduled_end(), 
                schedules.getCompleted_at(), 
                schedules.getNotes(), 
                schedules.getCreated_at(), 
                schedules.getUpdated_at(), 
                schedules.getProperties().getNickname(), 
                schedules.getPlagues().getPlague_name(), 
                schedules.getServices().getService_name(), 
                schedules.getStatus().getStatus_name(), 
                schedules.getUsers().getUsername()
        );
    }

    // dto para input de cliente
    public Schedules schedulesToEntity(SchedulesInDTO dto, Integer userId) {

        Schedules schedules = new Schedules();
        schedules.setScheduled_start(dto.scheduled_start());
        schedules.setScheduled_end(dto.scheduled_end());
        schedules.setCompleted_at(dto.completed_at());
        schedules.setNotes(dto.notes());
        schedules.setCreated_by(userId);
        schedules.setProperties(entityManager.getReference(Properties.class, dto.properties()));
        schedules.setPlagues(entityManager.getReference(Plagues.class, dto.plagues()));
        schedules.setServices(entityManager.getReference(Services.class, dto.service()));
        schedules.setStatus(entityManager.getReference(Status.class, dto.status()));
        schedules.setUsers(entityManager.getReference(Users.class, dto.user()));
        
        return schedules;
    }

    // dto para input de cliente (edit)
    public Schedules updateSchedulesToEntity(SchedulesInDTO dto, Schedules schedules, Integer userId) {
        schedules.setScheduled_start(dto.scheduled_start());
        schedules.setScheduled_end(dto.scheduled_end());
        schedules.setCompleted_at(dto.completed_at());
        schedules.setNotes(dto.notes());
        schedules.setProperties(entityManager.getReference(Properties.class, dto.properties()));
        schedules.setPlagues(entityManager.getReference(Plagues.class, dto.plagues()));
        schedules.setServices(entityManager.getReference(Services.class, dto.service()));
        schedules.setStatus(entityManager.getReference(Status.class, dto.status()));
        schedules.setUsers(entityManager.getReference(Users.class, dto.user()));
        schedules.setUpdated_at(Instant.now().minusSeconds(zoneTime));
        schedules.setEdited_by(userId);
        
        return schedules;
    }
}


