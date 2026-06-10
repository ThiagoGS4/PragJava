package com.antiprag.prag.DTO;

import java.time.Instant;

public record CustomerOutDTO (
    Integer id,
    String name,
    String cpf,
    String cnpj,
    String phone,
    String email,
    Instant created_at
){}
