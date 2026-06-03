package com.antiprag.prag.Controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import com.antiprag.prag.DTO.CertificatesOutDTO;
import com.antiprag.prag.domain.UsuarioPrincipal;
import com.antiprag.prag.service.CertificatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequiredArgsConstructor
public class CertificatesController {
    private final CertificatesService certificatesService;

    @GetMapping("/certificates/{customerId}")
    public List<CertificatesOutDTO> listCertificates(@PathVariable("customerId") Integer id) {
        return certificatesService.listCertificates(id);
    }
    
}
