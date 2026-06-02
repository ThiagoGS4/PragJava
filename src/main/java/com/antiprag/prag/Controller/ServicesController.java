package com.antiprag.prag.Controller;

import com.antiprag.prag.domain.Services;
import com.antiprag.prag.service.ServicesService;
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
public class ServicesController {

    private ServicesService servicesService;

    public ServicesController(ServicesService servicesService) {
        this.servicesService = servicesService;
    }

    @GetMapping(path = "/services/{id}")
    public Services getServices(@PathVariable("id") Integer id) {
        return servicesService.getServices(id);
    }

    @GetMapping(path = "/services")
    public List<Services> ListServices() {
        return servicesService.ListServices();
    }

    @DeleteMapping(path = "/deletar/Services/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarServices(@PathVariable("id") Integer id) throws IOException {
        servicesService.deletarServices(id);
    }

    @PutMapping(path = "/alterarServices")
    public void alterarServices(@RequestBody Services services) throws IOException {
        servicesService.alterarServices(services);
    }

    @PostMapping(path = "/inserirServices")
    public void inserirServices(@RequestBody Services services) throws IOException {
        servicesService.inserirServices(services);
    }
}
