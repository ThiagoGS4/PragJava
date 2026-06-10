package com.antiprag.prag.DTO;
import java.time.Instant;

public record SchedulesInDTO (
    Integer id,
    Instant scheduled_start,
    Instant scheduled_end,
    Instant completed_at,
    String notes,
    Integer properties,
    Integer plagues,
    Integer service,
    Integer status,
    Integer users
){}
