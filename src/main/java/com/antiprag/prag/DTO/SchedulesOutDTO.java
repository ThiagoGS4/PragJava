package com.antiprag.prag.DTO;

import java.time.Instant;

public record SchedulesOutDTO (
    Integer id,
    Instant scheduled_start,
    Instant scheduled_end,
    Instant completed_at,
    String notes,
    Instant created_at,
    Instant updated_at,
    Property properties,
    Plague plagues,
    Service service,
    Status status,
    Users user
){
    public static record Property(
        Integer id,
        String name
    ) {}

    public static record Plague(
        Integer id,
        String name
    ) {}

    public static record Service(
        Integer id,
        String name
    ) {}

    public static record Status(
        Integer id,
        String name
    ) {}
    public static record Users(
        Integer id,
        String name
    ) {}
}
