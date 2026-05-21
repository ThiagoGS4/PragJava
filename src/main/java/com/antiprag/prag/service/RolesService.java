package com.antiprag.prag.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import com.antiprag.prag.domain.Roles;
import com.antiprag.prag.repository.RolesRepository;

@Service
public class RolesService {

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    AuthenticationManager authManager;

    public RolesService(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    public Roles getRoles(Integer id) {
        return rolesRepository.findById(id).orElse(null);
    }

    public List<Roles> ListRoles() {
        return rolesRepository.findAll();
    }

    public void deletarRoles(int idRoles) {
        rolesRepository.deleteById(idRoles);
    }

    public void alterarRoles(Roles Roles) {
        rolesRepository.save(Roles);
    }

    public void inserirRoles(Roles roles) {
        rolesRepository.save(roles);
    }
}
