package com.antiprag.prag.Controller;

import com.antiprag.prag.domain.Admin;
import com.antiprag.prag.service.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tools.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping(path = "/admin/{id}")
    public Admin getAdmin(@PathVariable("id") Integer id) {
        return adminService.getAdmin(id);
    }

    @GetMapping(path = "/admin")
    public List<Admin> ListAdmin() {
        return adminService.ListAdmin();
    }

    @DeleteMapping(path = "/deletar/Admin/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarAdmin(@PathVariable("id") Integer id) throws IOException {
        adminService.deletarAdmin(id);
    }

    @PutMapping(path = "/alterarAdmin", consumes = {"application/json", "application/x-www-form-urlencoded"})
    public void alterarAdmin(@RequestBody String dados) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
       Admin admin = mapper.readValue(dados, Admin.class);
        adminService.alterarAdmin(admin);
    }

    @PostMapping(path = "/inserirAdmin", consumes = {"application/json", "application/x-www-form-urlencoded"})
    public void inserirAdmin(@RequestBody String dados) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Admin admin = mapper.readValue(dados, Admin.class);
        adminService.inserirAdmin(admin);
    }
    
    @PostMapping("/registrar")
    public Admin register(@RequestBody Admin usuario) {
        return adminService.register(usuario);

    }

    @PostMapping("/logar")
    public String login(@RequestBody Admin admin) {

        return adminService.verify(admin);
    }
    
    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");


    }
}