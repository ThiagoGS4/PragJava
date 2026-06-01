package com.antiprag.prag.Controller;
import com.antiprag.prag.domain.Privilege;
import com.antiprag.prag.service.PrivilegeService;
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
public class PrivilegeController {

    private PrivilegeService privilegeService;

    public PrivilegeController(PrivilegeService privilegeService) {
        this.privilegeService = privilegeService;
    }

    @GetMapping(path = "/privilege/{id}")
    public Privilege getPrivilege(@PathVariable("id") Integer id) {
        return privilegeService.getPrivilege(id);
    }

    @GetMapping(path = "/privilege")
    public List<Privilege> ListPrivilege() {
        return privilegeService.ListPrivilege();
    }

    @DeleteMapping(path = "/deletar/Privilege/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarPrivilege(@PathVariable("id") Integer id) throws IOException {
        privilegeService.deletarPrivilege(id);
    }

    @PutMapping(path = "/alterarPrivilege")
    public void alterarPrivilege(@RequestBody Privilege privilege) throws IOException {
        privilegeService.alterarPrivilege(privilege);
    }

    @PostMapping(path = "/inserirPrivilege")
    public void inserirPrivilege(@RequestBody Privilege privilege) throws IOException {
        privilegeService.inserirPrivilege(privilege);
    }
}