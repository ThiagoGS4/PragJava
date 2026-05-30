package com.antiprag.prag.DTO;

import java.util.Set;

public record PropertiesInDTO (
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
    Integer is_active
    //Set<Integer> customer
){}
