package com.antiprag.prag.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.antiprag.prag.domain.Plagues;
import com.antiprag.prag.repository.PlaguesRepository;

@Service
public class PlaguesService {

    @Autowired
    private PlaguesRepository plaguesRepository;


    public PlaguesService(PlaguesRepository plaguesRepository) {
        this.plaguesRepository = plaguesRepository;
    }

    public Plagues getPlagues(Integer id) {
        return plaguesRepository.findById(id).orElse(null);
    }

    public List<Plagues> ListPlagues() {
        return plaguesRepository.findAll();
    }

    public void deletarPlagues(int idPlagues) {
        plaguesRepository.deleteById(idPlagues);
    }

    public void alterarPlagues(Plagues Plagues) {
        plaguesRepository.save(Plagues);
    }

    public void inserirPlagues(Plagues plagues) {
        plaguesRepository.save(plagues);
    }
}
