package com.antiprag.prag.Controller;

import com.antiprag.prag.domain.Properties;
import com.antiprag.prag.service.PropertiesService;
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
public class PropertiesController {

    private PropertiesService propertiesService;

    public PropertiesController(PropertiesService propertiesService) {
        this.propertiesService = propertiesService;
    }

    @GetMapping(path = "/properties/{id}")
    public Properties getProperties(@PathVariable("id") Integer id) {
        return propertiesService.getProperties(id);
    }

    @GetMapping(path = "/properties")
    public List<Properties> ListProperties() {
        return propertiesService.ListProperties();
    }

    @DeleteMapping(path = "/deletar/Properties/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarProperties(@PathVariable("id") Integer id) throws IOException {
        propertiesService.deletarProperties(id);
    }

    @PutMapping(path = "/alterarProperties", consumes = {"application/json", "application/x-www-form-urlencoded"})
    public void alterarProperties(@RequestBody String dados) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
       Properties properties = mapper.readValue(dados, Properties.class);
        propertiesService.alterarProperties(properties);
    }

    @PostMapping(path = "/inserirProperties", consumes = {"application/json", "application/x-www-form-urlencoded"})
    public void inserirProperties(@RequestBody String dados) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Properties properties = mapper.readValue(dados, Properties.class);
        propertiesService.inserirProperties(properties);
    }
}
