package com.antiprag.prag.service;

import com.antiprag.prag.domain.Roles;
import com.antiprag.prag.domain.Users;

import java.security.Permission;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
    
    public Users findUserByUsername(String username) throws UsernameNotFoundException {
        Users users = usersRepository.findByUsername(username);
        if (users == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        
        return users;
    }

    public Collection<? extends GrantedAuthority> getAuthorities(String username) {
        Users users = usersRepository.findByUsername(username);
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Roles roles : users.getRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + roles.getName()));
            for (com.antiprag.prag.domain.Permission permission : roles.getPermissions()) {
                authorities.add(new SimpleGrantedAuthority(permission.getName()));
            }
        }
        return authorities;
    }
}
