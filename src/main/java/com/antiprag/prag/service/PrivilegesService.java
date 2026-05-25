package com.antiprag.prag.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.antiprag.prag.domain.Privileges;
import com.antiprag.prag.repository.PrivilegesRepository;

@Service
public class PrivilegesService {

    @Autowired
    private PrivilegesRepository privilegesRepository;


    public PrivilegesService(PrivilegesRepository privilegesRepository) {
        this.privilegesRepository = privilegesRepository;
    }

    public Privileges getPrivileges(Integer id) {
        return privilegesRepository.findById(id).orElse(null);
    }

    public List<Privileges> ListPrivileges() {
        return privilegesRepository.findAll();
    }

    public void deletarPrivileges(int idPrivileges) {
        privilegesRepository.deleteById(idPrivileges);
    }

    public void alterarPrivileges(Privileges Privileges) {
        privilegesRepository.save(Privileges);
    }

    public void inserirPrivileges(Privileges privileges) {
        privilegesRepository.save(privileges);
    }
}