package com.antiprag.prag.DTO;

import java.time.Instant;
import java.util.Set;

public record UsersOutDTO (
    String username,
    Boolean is_active,
    Instant created_at,
    Set<String> roles
){}
