package com.antiprag.prag.mapper;

import org.springframework.stereotype.Component;

import com.antiprag.prag.DTO.TokensDTO;

@Component
public class TokensMapper {
    
    public TokensDTO tokensOut(String accesstoken,String refreshToken){
        TokensDTO tokens = new TokensDTO(
            accesstoken,
            refreshToken
        );
        return tokens;
    }

}
