package com.antiprag.prag.DTO;

import java.sql.Date;
import java.time.Instant;
import java.util.Set;

public record SchedulesOutDTO (
    Date scheduled_start,
    Date scheduled_end,
    Date scheduled_at,
    String notes,
    Instant created_at,
    Date deleted_at,
    Date updated_at/*,
    Set<String> properties,
    Set<String> plagues,
    Set<String> service,
    Set<String> status,
    Set<String> user*/
){}

