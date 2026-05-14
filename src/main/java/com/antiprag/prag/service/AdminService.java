package com.antiprag.prag.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.antiprag.prag.domain.Admin;
import com.antiprag.prag.repository.AdminRepository;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;
    
    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authManager;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Admin getAdmin(Integer id) {
        return adminRepository.findById(id).orElse(null);
    }

    public List<Admin> ListAdmin() {
        return adminRepository.findAll();
    }

    public void deletarAdmin(int idAdmin) {
        adminRepository.deleteById(idAdmin);
    }

    public void alterarAdmin(Admin Admin) {
        adminRepository.save(Admin);
    }

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    
    public void inserirAdmin(Admin admin) {
        admin.setSenha_hash(encoder.encode(admin.getSenha_hash()));
        adminRepository.save(admin);
    }
    
    public Admin register(Admin admin) {
        admin.setSenha_hash(encoder.encode(admin.getSenha_hash()));
        adminRepository.save(admin);
        return admin;
    }

    public String verify(Admin admin) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(admin.getNome(), admin.getSenha_hash()));
   if (authentication.isAuthenticated()) {
         return jwtService.generateToken(admin.getNome())  ;
        } else {
            return "Falha";
        }
    }

}