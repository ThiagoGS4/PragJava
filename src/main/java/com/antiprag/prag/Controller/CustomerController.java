package com.antiprag.prag.Controller;

import com.antiprag.prag.domain.Customer;
import com.antiprag.prag.service.CustomerService;
import tools.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(path = "/customer/{id}")
    public Customer getCustomer(@PathVariable("id") Integer id) {
        return customerService.getCustomer(id);
    }

    @GetMapping(path = "/customer")
    public List<Customer> ListCustomer() {
        return customerService.ListCustomer();
    }

    @DeleteMapping(path = "/deletar/Customer/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarCustomer(@PathVariable("id") Integer id) throws IOException {
        customerService.deletarCustomer(id);
    }

    @PutMapping(path = "/alterarCustomer")
    public void alterarCustomer(@RequestBody Customer customer) throws IOException {
        customerService.alterarCustomer(customer);
    }

    @PostMapping(path = "/inserirCustomer")
    public void inserirCustomer(@RequestBody Customer customer) throws IOException {
        customerService.inserirCustomer(customer);
    }
}
