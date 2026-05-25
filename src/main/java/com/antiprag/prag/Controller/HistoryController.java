package com.antiprag.prag.Controller;

import com.antiprag.prag.domain.History;
import com.antiprag.prag.service.HistoryService;
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
public class HistoryController {

    private HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping(path = "/history/{id}")
    public History getHistory(@PathVariable("id") Integer id) {
        return historyService.getHistory(id);
    }

    @GetMapping(path = "/history")
    public List<History> ListHistory() {
        return historyService.ListHistory();
    }

    @DeleteMapping(path = "/deletar/History/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarHistory(@PathVariable("id") Integer id) throws IOException {
        historyService.deletarHistory(id);
    }

    @PutMapping(path = "/alterarHistory", consumes = {"application/json", "application/x-www-form-urlencoded"})
    public void alterarHistory(@RequestBody String dados) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
       History history = mapper.readValue(dados, History.class);
        historyService.alterarHistory(history);
    }

    @PostMapping(path = "/inserirHistory", consumes = {"application/json", "application/x-www-form-urlencoded"})
    public void inserirHistory(@RequestBody String dados) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        History history = mapper.readValue(dados, History.class);
        historyService.inserirHistory(history);
    }
}
