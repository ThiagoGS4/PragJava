package com.antiprag.prag.Controller;

import com.antiprag.prag.domain.Status;
import com.antiprag.prag.service.StatusService;
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
public class StatusController {

    private StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping(path = "/status/{id}")
    public Status getStatus(@PathVariable("id") Integer id) {
        return statusService.getStatus(id);
    }

    @GetMapping(path = "/status")
    public List<Status> ListStatus() {
        return statusService.ListStatus();
    }

    @DeleteMapping(path = "/status/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarStatus(@PathVariable("id") Integer id) throws IOException {
        statusService.deletarStatus(id);
    }

    @PutMapping(path = "/alterarStatus")
    public Status alterarStatus(@RequestBody Status status) throws IOException {
        return statusService.alterarStatus(status);
    }

    @PostMapping(path = "/inserirStatus")
    public Status inserirStatus(@RequestBody Status status) throws IOException {
        return statusService.inserirStatus(status);
    }
}
