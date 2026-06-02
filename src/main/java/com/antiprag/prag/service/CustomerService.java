package com.antiprag.prag.service;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.antiprag.prag.DTO.CustomerInDTO;
import com.antiprag.prag.DTO.CustomerOutDTO;
import com.antiprag.prag.domain.Customer;
import com.antiprag.prag.domain.UsuarioPrincipal;
import com.antiprag.prag.mapper.CustomerMapper;
import com.antiprag.prag.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    public CustomerOutDTO getCustomer(Integer id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        return customerMapper.customerToOutDto(customer);
    }

    public List<CustomerOutDTO> ListCustomer() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::customerToOutDto)
                .toList();
    }

    public void deletarCustomer(int idCustomer) {
        customerRepository.deleteById(idCustomer);
    }

    public CustomerOutDTO alterarCustomer(CustomerInDTO customer, UsuarioPrincipal usuarioPrincipal) {
        Integer userId = usuarioPrincipal.getId();
        Customer customerEntity = customerRepository.findById(customer.id())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

        customerMapper.updateCustomerEntity(customer, customerEntity, userId);
        
        return customerMapper.customerToOutDto(customerRepository.save(customerEntity));
    }

    public CustomerOutDTO inserirCustomer(CustomerInDTO customer, UsuarioPrincipal usuarioPrincipal) {
        Integer userId = usuarioPrincipal.getId();

        Customer customerEntity = customerMapper.customerToEntity(customer, userId);
        return customerMapper.customerToOutDto(customerRepository.save(customerEntity));
    }

}
