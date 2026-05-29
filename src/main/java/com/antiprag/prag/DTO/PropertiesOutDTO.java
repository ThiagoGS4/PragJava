package com.antiprag.prag.DTO;

import java.time.Instant;
import java.util.Set;

public record PropertiesOutDTO (
    String nickname,
    String cep,
    String street,
    String number,
    String complement,
    String district,
    String city,
    String state,
    String country,
    String formatted_address,
    String google_place_id,
    Integer latitude,
    Integer longitude,
    String property_type,
    Integer is_active,
    Instant created_at,
    Integer customer_id
    //Set<String> customer
){}
