package com.antiprag.prag.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.antiprag.prag.domain.Customer;
import com.antiprag.prag.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getCustomer(Integer id) {
        return customerRepository.findById(id).orElse(null);
    }

    public List<Customer> ListCustomer() {
        return customerRepository.findAll();
    }

    public void deletarCustomer(int idCustomer) {
        customerRepository.deleteById(idCustomer);
    }

    public void alterarCustomer(Customer Customer) {
        customerRepository.save(Customer);
    }

    public void inserirCustomer(Customer customer) {
        customerRepository.save(customer);
    }
}
