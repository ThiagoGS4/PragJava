package com.antiprag.prag.Controller;

import com.antiprag.prag.domain.Users;
import com.antiprag.prag.service.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.csrf.CsrfToken;
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
public class UsersController {

    private UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping(path = "/users/{id}")
    public Users getUsers(@PathVariable("id") Integer id) {
        return usersService.getUsers(id);
    }

    @GetMapping(path = "/users")
    public List<Users> ListUsers() {
        return usersService.ListUsers();
    }

    @DeleteMapping(path = "/deletar/Users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarUsers(@PathVariable("id") Integer id) throws IOException {
        usersService.deletarUsers(id);
    }

    @PutMapping(path = "/alterarUsers")
    public void alterarUsers(@RequestBody Users users) throws IOException {
        usersService.alterarUsers(users);
    }

    @PostMapping(path = "/inserirUsers")
    public void inserirUsers(@RequestBody Users users) throws IOException {
        usersService.inserirUsers(users);
    }
    
    @PostMapping("/registrar")
    public Users register(@RequestBody Users users) {
        return usersService.register(users);

    }

    @PostMapping("/logar")
    public String login(@RequestBody Users users) {

        return usersService.verify(users);
    }
    
    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");


    }
}