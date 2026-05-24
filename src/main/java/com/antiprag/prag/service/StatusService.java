package com.antiprag.prag.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import com.antiprag.prag.domain.Status;
import com.antiprag.prag.repository.StatusRepository;

@Service
public class StatusService {

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    AuthenticationManager authManager;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public Status getStatus(Integer id) {
        return statusRepository.findById(id).orElse(null);
    }

    public List<Status> ListStatus() {
        return statusRepository.findAll();
    }

    public void deletarStatus(int idStatus) {
        statusRepository.deleteById(idStatus);
    }

    public void alterarStatus(Status Status) {
        statusRepository.save(Status);
    }

    public void inserirStatus(Status status) {
        statusRepository.save(status);
    }
}
