package com.antiprag.prag.service;

import com.antiprag.prag.domain.Admin;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.antiprag.prag.domain.UsuarioPrincipal;
import com.antiprag.prag.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UsuarioDetailService implements UserDetailsService{
    
    @Autowired
    private AdminRepository adminRepository;
    
    @Override
    public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByNome(nome);
        if (admin == null) {
            System.out.println("Usuário não encontrado");
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        
        return new UsuarioPrincipal(admin);
    }
    
}
