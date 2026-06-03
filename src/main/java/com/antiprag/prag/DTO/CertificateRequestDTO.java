package com.antiprag.prag.DTO;

public record CertificateRequestDTO(
    String name,
    String cpf,
    String cnpj,
    String email
) {}
