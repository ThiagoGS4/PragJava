package com.antiprag.prag.service;

import com.antiprag.prag.domain.Privileges;
import com.antiprag.prag.domain.Roles;
import com.antiprag.prag.domain.Users;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.antiprag.prag.domain.UsuarioPrincipal;
import com.antiprag.prag.repository.UsersRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UsuarioDetailService implements UserDetailsService{
    
    @Autowired
    private UsersRepository usersRepository;
    
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Users users = usersRepository.findByName(name);
        if (users == null) {
            System.out.println("Usuário não encontrado");
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        
        return new UsuarioPrincipal(users);
    }
    private Collection<? extends GrantedAuthority> getAuthorities(
      Collection<Roles> roles) {
 
        return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<String> getPrivileges(Collection<Roles> roles) {
 
        List<String> privileges = new ArrayList<>();
        List<Privileges> collection = new ArrayList<>();
        for (Roles role : roles) {
            privileges.add(role.getName());
            collection.addAll(role.getPrivileges());
        }
        for (Privileges item : collection) {
            privileges.add(item.getName());
        }
        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
}
