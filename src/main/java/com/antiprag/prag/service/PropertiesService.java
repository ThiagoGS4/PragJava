package com.antiprag.prag.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.antiprag.prag.DTO.PropertiesInDTO;
import com.antiprag.prag.DTO.PropertiesOutDTO;
import com.antiprag.prag.domain.Properties;
import com.antiprag.prag.mapper.PropertiesMapper;
import com.antiprag.prag.repository.PropertiesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PropertiesService {

    private final PropertiesRepository propertiesRepository;

    private final PropertiesMapper propertiesMapper;

    public PropertiesOutDTO getProperties(Integer id) {
        Properties properties = propertiesRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Properties not found"));

        return propertiesMapper.propertiesToOutDto(properties);
    }

    public List<PropertiesOutDTO> ListProperties() {
        return propertiesRepository.findAll()
            .stream()
            .map(propertiesMapper::propertiesToOutDto)
            .toList();
    }

    public void deletarProperties(int idProperties) {
        propertiesRepository.deleteById(idProperties);
    }

    public void alterarProperties(Properties Properties) {
        propertiesRepository.save(Properties);
    }
    
    public PropertiesInDTO inserirProperties(PropertiesInDTO properties) {
        return properties;
    }

}
