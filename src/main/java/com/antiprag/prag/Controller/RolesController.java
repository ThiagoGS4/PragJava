package com.antiprag.prag.Controller;
import com.antiprag.prag.domain.Roles;
import com.antiprag.prag.service.RolesService;
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
public class RolesController {

    private RolesService rolesService;

    public RolesController(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @GetMapping(path = "/roles/{id}")
    public Roles getRoles(@PathVariable("id") Integer id) {
        return rolesService.getRoles(id);
    }

    @GetMapping(path = "/roles")
    public List<Roles> ListRoles() {
        return rolesService.ListRoles();
    }

    @DeleteMapping(path = "/deletar/Roles/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarRoles(@PathVariable("id") Integer id) throws IOException {
        rolesService.deletarRoles(id);
    }

    @PutMapping(path = "/alterarRoles", consumes = {"application/json", "application/x-www-form-urlencoded"})
    public void alterarRoles(@RequestBody String dados) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
       Roles roles = mapper.readValue(dados, Roles.class);
        rolesService.alterarRoles(roles);
    }

    @PostMapping(path = "/inserirRoles", consumes = {"application/json", "application/x-www-form-urlencoded"})
    public void inserirRoles(@RequestBody String dados) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Roles roles = mapper.readValue(dados, Roles.class);
        rolesService.inserirRoles(roles);
    }
}