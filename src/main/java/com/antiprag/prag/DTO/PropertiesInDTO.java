package com.antiprag.prag.DTO;

public record PropertiesInDTO (
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
    Integer customer
){}
