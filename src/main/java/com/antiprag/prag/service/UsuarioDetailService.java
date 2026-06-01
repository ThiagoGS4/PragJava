package com.antiprag.prag.service;

import com.antiprag.prag.domain.Users;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.antiprag.prag.domain.UsuarioPrincipal;
import com.antiprag.prag.repository.UsersRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioDetailService implements UserDetailsService{
    
    private final UsersRepository usersRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = usersRepository.findByUsername(username);
        if (users == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        
        return new UsuarioPrincipal(users);
    }
}
