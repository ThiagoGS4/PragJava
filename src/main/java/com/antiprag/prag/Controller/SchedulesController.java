package com.antiprag.prag.Controller;

import com.antiprag.prag.DTO.SchedulesInDTO;
import com.antiprag.prag.DTO.SchedulesOutDTO;
import com.antiprag.prag.domain.UsuarioPrincipal;
import com.antiprag.prag.service.SchedulesService;
import java.io.IOException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public SchedulesOutDTO getSchedules(@PathVariable("id") Integer id) {
        return schedulesService.getSchedules(id);
    }

    @GetMapping(path = "/schedules")
    public List<SchedulesOutDTO> ListSchedules() {
        return schedulesService.ListSchedules();
    }

    @DeleteMapping(path = "schedules/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarSchedules(@PathVariable("id") Integer id) throws IOException {
        schedulesService.deletarSchedules(id);
    }

    @PutMapping(path = "/alterarSchedules")
    public SchedulesOutDTO alterarSchedules(@RequestBody SchedulesInDTO schedules, @AuthenticationPrincipal UsuarioPrincipal usuarioPrincipal) throws IOException {
        return schedulesService.alterarSchedules(schedules, usuarioPrincipal);
    }

    @PostMapping(path = "/inserirSchedules")
    public SchedulesOutDTO inserirSchedules(@RequestBody SchedulesInDTO schedules, @AuthenticationPrincipal UsuarioPrincipal usuarioPrincipal) throws IOException {
        return schedulesService.inserirSchedules(schedules, usuarioPrincipal);
    }
}
