package com.antiprag.prag.DTO;

import java.sql.Date;
import java.util.Set;

public record SchedulesInDTO (
    Date scheduled_start,
    Date scheduled_end,
    Date scheduled_at,
    String notes/* ,
    Set<Integer> properties,
    Set<Integer> plagues,
    Set<Integer> service,
    Set<Integer> status,
    Set<Integer> user*/
){}
