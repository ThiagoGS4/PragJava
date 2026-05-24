package com.antiprag.prag.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import com.antiprag.prag.domain.Services;
import com.antiprag.prag.repository.ServicesRepository;

@Service
public class ServicesService {

    @Autowired
    private ServicesRepository servicesRepository;

    @Autowired
    AuthenticationManager authManager;

    public ServicesService(ServicesRepository servicesRepository) {
        this.servicesRepository = servicesRepository;
    }

    public Services getServices(Integer id) {
        return servicesRepository.findById(id).orElse(null);
    }

    public List<Services> ListServices() {
        return servicesRepository.findAll();
    }

    public void deletarServices(int idServices) {
        servicesRepository.deleteById(idServices);
    }

    public void alterarServices(Services Services) {
        servicesRepository.save(Services);
    }

    public void inserirServices(Services services) {
        servicesRepository.save(services);
    }
}
