package com.antiprag.prag.Controller;

import com.antiprag.prag.DTO.PropertiesInDTO;
import com.antiprag.prag.DTO.PropertiesOutDTO;
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
    public PropertiesOutDTO getProperties(@PathVariable("id") Integer id) {
        return propertiesService.getProperties(id);
    }

    @GetMapping(path = "/properties")
    public List<PropertiesOutDTO> ListProperties() {
        return propertiesService.ListProperties();
    }

    @DeleteMapping(path = "/deletar/Properties/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarProperties(@PathVariable("id") Integer id) throws IOException {
        propertiesService.deletarProperties(id);
    }

    @PutMapping(path = "/alterarProperties")
    public void alterarProperties(@RequestBody Properties properties) throws IOException {

        propertiesService.alterarProperties(properties);
    }

    @PostMapping(path = "/inserirProperties")
    public void inserirProperties(@RequestBody PropertiesInDTO properties) throws IOException {
        propertiesService.inserirProperties(properties);
    }
}
