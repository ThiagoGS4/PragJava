package com.antiprag.prag.service;

import com.antiprag.prag.mapper.CertificatesMapper;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.antiprag.prag.DTO.CertificatesOutDTO;
import com.antiprag.prag.domain.Customer;
import com.antiprag.prag.repository.CertificatesRepository;
import com.antiprag.prag.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CertificatesService {
    
    private final CustomerRepository customerRepository;
    private final CertificatesMapper certificatesMapper;
    private final CertificatesRepository certificatesRepository;

    public List<CertificatesOutDTO> listCertificates(Integer customerId){
        Customer customer = customerRepository.findById(customerId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found with id: " + customerId));

        return certificatesRepository.findByCustomers(customer)
                .stream()
                .map(certificatesMapper::certificateToOut)
                .toList();
    }
}
