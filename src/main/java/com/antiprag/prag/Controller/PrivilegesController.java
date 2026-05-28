/* package com.antiprag.prag.Controller;

import com.antiprag.prag.domain.Privileges;
import com.antiprag.prag.service.PrivilegesService;
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
public class PrivilegesController {

    private PrivilegesService privilegesService;

    public PrivilegesController(PrivilegesService privilegesService) {
        this.privilegesService = privilegesService;
    }

    @GetMapping(path = "/privileges/{id}")
    public Privileges getPrivileges(@PathVariable("id") Integer id) {
        return privilegesService.getPrivileges(id);
    }

    @GetMapping(path = "/privileges")
    public List<Privileges> ListPrivileges() {
        return privilegesService.ListPrivileges();
    }

    @DeleteMapping(path = "/deletar/Privileges/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarPrivileges(@PathVariable("id") Integer id) throws IOException {
        privilegesService.deletarPrivileges(id);
    }

    @PutMapping(path = "/alterarPrivileges", consumes = {"application/json", "application/x-www-form-urlencoded"})
    public void alterarPrivileges(@RequestBody String dados) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
       Privileges privileges = mapper.readValue(dados, Privileges.class);
        privilegesService.alterarPrivileges(privileges);
    }

    @PostMapping(path = "/inserirPrivileges", consumes = {"application/json", "application/x-www-form-urlencoded"})
    public void inserirPrivileges(@RequestBody String dados) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Privileges privileges = mapper.readValue(dados, Privileges.class);
        privilegesService.inserirPrivileges(privileges);
    }
}
 */