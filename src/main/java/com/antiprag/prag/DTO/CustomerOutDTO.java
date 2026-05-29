package com.antiprag.prag.DTO;

import java.sql.Date;

public record CustomerOutDTO (
    String name,
    String cpf,
    String cnpj,
    String phone,
    String email,
    Integer status_id,
    Date created_at
){}
