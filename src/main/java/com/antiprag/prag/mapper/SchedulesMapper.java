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
@ConfigurationProperties(prefix = "app.jwt-zone")
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
                schedules.getId(),
                schedules.getScheduled_start(),
                schedules.getScheduled_end(),
                schedules.getCompleted_at(),
                schedules.getNotes(),
                schedules.getCreated_at(),
                schedules.getUpdated_at(),
                schedules.getProperties() != null
                        ? new SchedulesOutDTO.Property(
                                schedules.getProperties().getId(),
                                schedules.getProperties().getNickname())
                        : null,
                schedules.getPlagues() != null
                        ? new SchedulesOutDTO.Plague(
                                schedules.getPlagues().getId(),
                                schedules.getPlagues().getPlague_name())
                        : null,
                schedules.getServices() != null
                        ? new SchedulesOutDTO.Service(
                                schedules.getServices().getId(),
                                schedules.getServices().getService_name())
                        : null,
                schedules.getStatus() != null
                        ? new SchedulesOutDTO.Status(
                                schedules.getStatus().getId(),
                                schedules.getStatus().getStatus_name())
                        : null,
                schedules.getUsers() != null
                        ? new SchedulesOutDTO.Users(
                                schedules.getUsers().getId(),
                                schedules.getUsers().getUsername())
                        : null);
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
        schedules.setUsers(entityManager.getReference(Users.class, dto.users()));

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
        schedules.setUsers(entityManager.getReference(Users.class, dto.users()));
        schedules.setUpdated_at(Instant.now().minusSeconds(zoneTime));
        schedules.setEdited_by(userId);

        return schedules;
    }
}
