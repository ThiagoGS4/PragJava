package com.antiprag.prag.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.antiprag.prag.domain.Properties;
import com.antiprag.prag.repository.PropertiesRepository;

@Service
public class PropertiesService {

    @Autowired
    private PropertiesRepository propertiesRepository;


    public PropertiesService(PropertiesRepository propertiesRepository) {
        this.propertiesRepository = propertiesRepository;
    }

    public Properties getProperties(Integer id) {
        return propertiesRepository.findById(id).orElse(null);
    }

    public List<Properties> ListProperties() {
        return propertiesRepository.findAll();
    }

    public void deletarProperties(int idProperties) {
        propertiesRepository.deleteById(idProperties);
    }

    public void alterarProperties(Properties Properties) {
        propertiesRepository.save(Properties);
    }

    public void inserirProperties(Properties properties) {
        propertiesRepository.save(properties);
    }
}
