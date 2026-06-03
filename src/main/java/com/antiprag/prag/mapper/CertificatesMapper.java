package com.antiprag.prag.mapper;

import com.antiprag.prag.domain.Certificates;

import org.springframework.stereotype.Component;

import com.antiprag.prag.DTO.CertificatesOutDTO;

@Component
public class CertificatesMapper {
    public CertificatesOutDTO certificateToOut(Certificates certificate){
        return new CertificatesOutDTO(
            certificate.getUrl()
        );
    }
}
