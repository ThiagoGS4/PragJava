package com.antiprag.prag.mapper;

import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.antiprag.prag.DTO.CustomerInDTO;
import com.antiprag.prag.DTO.CustomerOutDTO;
import com.antiprag.prag.domain.Customer;
import com.antiprag.prag.domain.Customer_status;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Component
public class CustomerMapper {
    
    @PersistenceContext
    private EntityManager entityManager;

    // dto para output de dados do(s) clientes(s)
    public CustomerOutDTO customerToOutDto(Customer customer) {
        return new CustomerOutDTO(
                customer.getName(),
                customer.getCpf(),
                customer.getCnpj(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getStatus_id(),
                customer.getCreated_at()
        );
    }

    // dto para input de cliente
    public Customer customerToEntity(CustomerInDTO dto) {
        Customer customer = new Customer();
        customer.setName(dto.name());
        customer.setCpf(dto.cpf());
        customer.setCnpj(dto.cnpj());
        customer.setPhone(dto.phone());
        customer.setEmail(dto.email());
        customer.setStatus_id(dto.status_id()); //TODO ver como vai fazer o status do cliente

        return customer;
    }
}
