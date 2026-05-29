package com.antiprag.prag.DTO;

import java.time.Instant;

public record CustomerOutDTO (
    String name,
    String cpf,
    String cnpj,
    String phone,
    String email,
    Integer status_id,
    Instant created_at
){}
