package com.antiprag.prag.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.antiprag.prag.DTO.CustomerInDTO;
import com.antiprag.prag.DTO.CustomerOutDTO;
import com.antiprag.prag.domain.Customer;
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

    public void alterarCustomer(Customer Customer) {
        customerRepository.save(Customer);
    }
    
    public CustomerInDTO register(CustomerInDTO customer) {
        return customer;
    }

}
