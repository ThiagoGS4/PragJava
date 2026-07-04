package com.antiprag.prag.DTO;

import java.time.Instant;

public record PropertiesOutDTO (
    Integer id,
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
    //String google_place_id,
    //Double latitude,
    //Double longitude,
    String property_type,
    Integer is_active,
    Instant created_at,
    Customer customer
){
    public static record Customer(
        Integer id,
        String name
    ) {}
}
