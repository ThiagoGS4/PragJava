package com.antiprag.prag.Controller;

import com.antiprag.prag.mapper.CertificatesMapper;
import com.antiprag.prag.service.CertGenService;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import com.antiprag.prag.DTO.CertGenDTO;
import com.antiprag.prag.DTO.CertificatesOutDTO;
import com.antiprag.prag.service.CertificatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequiredArgsConstructor
public class CertificatesController {
    private final CertificatesMapper certificatesMapper;
    private final CertGenService certGenService;
    private final CertificatesService certificatesService;

    @GetMapping("/certificates/{customerId}")
    public List<CertificatesOutDTO> listCertificates(@PathVariable("customerId") Integer id) {
        return certificatesService.listCertificates(id);
    }

    @PostMapping("/certificates/generate")
    public CertificatesOutDTO postMethodName(@RequestBody CertGenDTO certGenDTO) {
        
        return certificatesMapper.certificateToOut(certGenService.generateMockCertificate(certGenDTO.customerId()));
    }
    
    
}
