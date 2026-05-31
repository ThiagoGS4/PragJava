package com.antiprag.prag.Controller;

import com.antiprag.prag.DTO.CustomerInDTO;
import com.antiprag.prag.DTO.CustomerOutDTO;
import com.antiprag.prag.domain.UsuarioPrincipal;
import com.antiprag.prag.service.CustomerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import java.io.IOException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping(path = "/customer/{id}")
    public CustomerOutDTO getCustomer(@PathVariable("id") Integer id) {
        return customerService.getCustomer(id);
    }

    @GetMapping(path = "/customer")
    public List<CustomerOutDTO> ListCustomer() {
        return customerService.ListCustomer();
    }

    @DeleteMapping(path = "/customer/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarCustomer(@PathVariable("id") Integer id) throws IOException {
        customerService.deletarCustomer(id);
    }

    @PutMapping(path = "/alterarCustomer")
    public CustomerOutDTO alterarCustomer(@Valid @RequestBody CustomerInDTO customer, @AuthenticationPrincipal UsuarioPrincipal usuarioPrincipal) throws IOException {
        return customerService.alterarCustomer(customer, usuarioPrincipal);
    }

    // TODO: adicionar esse @AuthenticationPrincipal em todos os lugares que precisam de dados do usuário logado.
    @PostMapping(path = "/inserirCustomer")
    public CustomerOutDTO inserirCustomer(@Valid @RequestBody CustomerInDTO customer, @AuthenticationPrincipal UsuarioPrincipal usuarioPrincipal) throws IOException {
        return customerService.inserirCustomer(customer, usuarioPrincipal);
    }
}
