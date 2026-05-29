package com.antiprag.prag.Controller;

import com.antiprag.prag.domain.Plagues;
import com.antiprag.prag.service.PlaguesService;
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
public class PlaguesController {

    private PlaguesService plaguesService;

    public PlaguesController(PlaguesService plaguesService) {
        this.plaguesService = plaguesService;
    }

    @GetMapping(path = "/plagues/{id}")
    public Plagues getPlagues(@PathVariable("id") Integer id) {
        return plaguesService.getPlagues(id);
    }

    @GetMapping(path = "/plagues")
    public List<Plagues> ListPlagues() {
        return plaguesService.ListPlagues();
    }

    @DeleteMapping(path = "/deletar/Plagues/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarPlagues(@PathVariable("id") Integer id) throws IOException {
        plaguesService.deletarPlagues(id);
    }

    @PutMapping(path = "/alterarPlagues")
    public void alterarPlagues(@RequestBody Plagues plagues) throws IOException {
        plaguesService.alterarPlagues(plagues);
    }

    @PostMapping(path = "/inserirPlagues")
    public void inserirPlagues(@RequestBody Plagues plagues) throws IOException {
        plaguesService.inserirPlagues(plagues);
    }
}
