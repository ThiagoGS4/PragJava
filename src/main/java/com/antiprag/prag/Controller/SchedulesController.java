package com.antiprag.prag.Controller;

import com.antiprag.prag.domain.Schedules;
import com.antiprag.prag.service.SchedulesService;
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
public class SchedulesController {

    private SchedulesService schedulesService;

    public SchedulesController(SchedulesService schedulesService) {
        this.schedulesService = schedulesService;
    }

    @GetMapping(path = "/schedules/{id}")
    public Schedules getSchedules(@PathVariable("id") Integer id) {
        return schedulesService.getSchedules(id);
    }

    @GetMapping(path = "/schedules")
    public List<Schedules> ListSchedules() {
        return schedulesService.ListSchedules();
    }

    @DeleteMapping(path = "/deletar/Schedules/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarSchedules(@PathVariable("id") Integer id) throws IOException {
        schedulesService.deletarSchedules(id);
    }

    @PutMapping(path = "/alterarSchedules")
    public void alterarSchedules(@RequestBody Schedules schedules) throws IOException {
        schedulesService.alterarSchedules(schedules);
    }

    @PostMapping(path = "/inserirSchedules")
    public void inserirSchedules(@RequestBody Schedules schedules) throws IOException {
        schedulesService.inserirSchedules(schedules);
    }
}
