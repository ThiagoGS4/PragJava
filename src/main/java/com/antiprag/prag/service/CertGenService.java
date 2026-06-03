package com.antiprag.prag.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.antiprag.prag.DTO.CertificateRequestDTO;
import com.antiprag.prag.DTO.CertificatesOutDTO;
import com.antiprag.prag.domain.Certificates;
import com.antiprag.prag.domain.Customer;
import com.antiprag.prag.repository.CertificatesRepository;
import com.antiprag.prag.repository.CustomerRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CertGenService {
    private final CustomerRepository customerRepository;
    private final CertificatesRepository certificatesRepository;
    private final RestClient certificateRestClient;

    @Transactional
    public Certificates generateMockCertificate(Integer customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        CertificateRequestDTO payload = new CertificateRequestDTO(
                customer.getName(),
                customer.getCpf(),
                customer.getCnpj(),
                customer.getEmail()
        );

        CertificatesOutDTO response = certificateRestClient.post()
                .uri("/certificates/mock")
                .body(payload)
                .retrieve()
                .body(CertificatesOutDTO.class);

        if (response == null || response.url() == null || response.url().isBlank()) {
            throw new RuntimeException("API de certificado não retornou URL");
        }

        Certificates certificate = new Certificates();
        certificate.setCustomers(customer);
        certificate.setUrl(response.url());

        return certificatesRepository.save(certificate);
    }
}
