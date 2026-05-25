package com.antiprag.prag.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.antiprag.prag.domain.Customer_status;
import com.antiprag.prag.repository.Customer_statusRepository;

@Service
public class Customer_statusService {

    @Autowired
    private Customer_statusRepository customer_statusRepository;


    public Customer_statusService(Customer_statusRepository customer_statusRepository) {
        this.customer_statusRepository = customer_statusRepository;
    }

    public Customer_status getCustomer_status(Integer id) {
        return customer_statusRepository.findById(id).orElse(null);
    }

    public List<Customer_status> ListCustomer_status() {
        return customer_statusRepository.findAll();
    }

    public void deletarCustomer_status(int idCustomer_status) {
        customer_statusRepository.deleteById(idCustomer_status);
    }

    public void alterarCustomer_status(Customer_status Customer_status) {
        customer_statusRepository.save(Customer_status);
    }

    public void inserirCustomer_status(Customer_status customer_status) {
        customer_statusRepository.save(customer_status);
    }
}