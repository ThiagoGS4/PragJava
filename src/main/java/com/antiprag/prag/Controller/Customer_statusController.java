/*package com.antiprag.prag.Controller;

import com.antiprag.prag.domain.Customer_status;
import com.antiprag.prag.service.Customer_statusService;
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
public class Customer_statusController {

    private Customer_statusService customer_statusService;

    public Customer_statusController(Customer_statusService customer_statusService) {
        this.customer_statusService = customer_statusService;
    }

    @GetMapping(path = "/customer_status/{id}")
    public Customer_status getCustomer_status(@PathVariable("id") Integer id) {
        return customer_statusService.getCustomer_status(id);
    }

    @GetMapping(path = "/customer_status")
    public List<Customer_status> ListCustomer_status() {
        return customer_statusService.ListCustomer_status();
    }

    @DeleteMapping(path = "/deletar/Customer_status/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarCustomer_status(@PathVariable("id") Integer id) throws IOException {
        customer_statusService.deletarCustomer_status(id);
    }

    @PutMapping(path = "/alterarCustomer_status")
    public void alterarCustomer_status(@RequestBody Customer_status customer_status) throws IOException {
        customer_statusService.alterarCustomer_status(customer_status);
    }

    @PostMapping(path = "/inserirCustomer_status")
    public void inserirCustomer_status(@RequestBody Customer_status customer_status) throws IOException {
        customer_statusService.inserirCustomer_status(customer_status);
    }
}*/
