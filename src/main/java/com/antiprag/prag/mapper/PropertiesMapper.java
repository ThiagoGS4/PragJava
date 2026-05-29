package com.antiprag.prag.mapper;

import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.antiprag.prag.DTO.PropertiesInDTO;
import com.antiprag.prag.DTO.PropertiesOutDTO;
import com.antiprag.prag.domain.Customer;
import com.antiprag.prag.domain.Properties;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Component
public class PropertiesMapper {
    
    @PersistenceContext
    private EntityManager entityManager;

    // dto para output de dados do(s) clientes(s)
    public PropertiesOutDTO propertiesToOutDto(Properties properties) {
        return new PropertiesOutDTO(
                properties.getNickname(),
                properties.getCep(),
                properties.getStreet(),
                properties.getNumber(),
                properties.getComplement(),
                properties.getDistrict(),
                properties.getCity(),
                properties.getState(),
                properties.getCountry(),
                properties.getFormatted_address(),
                properties.getGoogle_place_id(),
                properties.getLatitude(),
                properties.getLongitude(),
                properties.getProperty_type(),
                properties.getIs_active(),
                properties.getCreated_at(),
                properties.getCustomer_id()

        );
    }

    // dto para input de cliente
    public Properties propertiesToEntity(PropertiesInDTO dto) {
        Properties properties = new Properties();
        properties.setNickname(dto.nickname());
        properties.setCep(dto.cep());
        properties.setStreet(dto.street());
        properties.setNumber(dto.number());
        properties.setComplement(dto.complement());
        properties.setDistrict(dto.district());
        properties.setCity(dto.city());
        properties.setState(dto.state());
        properties.setCountry(dto.country());
        properties.setFormatted_address(dto.formatted_address());
        properties.setGoogle_place_id(dto.google_place_id());
        properties.setLatitude(dto.latitude());
        properties.setLongitude(dto.longitude());
        properties.setProperty_type(dto.property_type());
        properties.setIs_active(dto.is_active());
        properties.setCustomer_id(dto.customer_id());

        return properties;
    }
}

