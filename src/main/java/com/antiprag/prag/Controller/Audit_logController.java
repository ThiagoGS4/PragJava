package com.antiprag.prag.Controller;
import com.antiprag.prag.domain.Audit_log;
import com.antiprag.prag.service.Audit_logService;
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
public class Audit_logController {

    private Audit_logService audit_logService;

    public Audit_logController(Audit_logService audit_logService) {
        this.audit_logService = audit_logService;
    }

    @GetMapping(path = "/audit_log/{id}")
    public Audit_log getAudit_log(@PathVariable("id") Integer id) {
        return audit_logService.getAudit_log(id);
    }

    @GetMapping(path = "/audit_log")
    public List<Audit_log> ListAudit_log() {
        return audit_logService.ListAudit_log();
    }

    @DeleteMapping(path = "/deletar/Audit_log/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarAudit_log(@PathVariable("id") Integer id) throws IOException {
        audit_logService.deletarAudit_log(id);
    }

    @PutMapping(path = "/alterarAudit_log", consumes = {"application/json", "application/x-www-form-urlencoded"})
    public void alterarAudit_log(@RequestBody String dados) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
       Audit_log audit_log = mapper.readValue(dados, Audit_log.class);
        audit_logService.alterarAudit_log(audit_log);
    }

    @PostMapping(path = "/inserirAudit_log", consumes = {"application/json", "application/x-www-form-urlencoded"})
    public void inserirAudit_log(@RequestBody String dados) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Audit_log audit_log = mapper.readValue(dados, Audit_log.class);
        audit_logService.inserirAudit_log(audit_log);
    }
}