package com.antiprag.prag.DTO;

import java.time.Instant;

public record SchedulesOutDTO (
    Instant scheduled_start,
    Instant scheduled_end,
    Instant completed_at,
    String notes,
    Instant created_at,
    Instant updated_at,
    String properties,
    String plagues,
    String service,
    String status,
    String user
){}

