package com.antiprag.prag.DTO;

import java.util.Set;

public record UsersInDTO (
    String username,
    String password,
    Boolean is_active,
    Set<Integer> roles
){}
