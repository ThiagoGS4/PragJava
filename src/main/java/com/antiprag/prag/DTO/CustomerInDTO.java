package com.antiprag.prag.DTO;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CustomerInDTO (
    Integer id,

    @NotBlank(message = "Nome é obrigatório")
    String name,

    @Pattern(regexp = "^$|^\\d{11}$", message = "CPF deve ter 11 dígitos numéricos")
    String cpf,

    @Pattern(regexp = "^$|^\\d{14}$", message = "CNPJ deve ter 14 dígitos numéricos")
    String cnpj,

    @NotBlank(message = "Telefone é obrigatório")
    String phone,

    @Email(message = "Email inválido")
    @NotBlank(message = "Email é obrigatório")
    String email
) {
    @AssertTrue(message = "Informe apenas um documento: CPF ou CNPJ")
    public boolean isDocumentoValido() {
        boolean temCpf = cpf != null && !cpf.isBlank();
        boolean temCnpj = cnpj != null && !cnpj.isBlank();
        return temCpf ^ temCnpj;
    }
}
