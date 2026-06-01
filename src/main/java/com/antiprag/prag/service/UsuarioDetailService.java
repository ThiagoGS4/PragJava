package com.antiprag.prag.service;

import com.antiprag.prag.domain.Privilege;
import com.antiprag.prag.domain.Roles;
import com.antiprag.prag.domain.Users;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
        getAuthorities(users.getRoles());
        return new UsuarioPrincipal(users);
    }
    
    public Users findUserByUsername(String username) throws UsernameNotFoundException {
        Users users = usersRepository.findByUsername(username);
        if (users == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        
        return users;
    }

     private Collection<? extends GrantedAuthority> getAuthorities(
      Collection<Roles> roles) {
 
        return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<String> getPrivileges(Collection<Roles> roles) {
 
        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();
        for (Roles role : roles) {
            privileges.add(role.getName());
            collection.addAll(role.getPrivileges());
        }
        for (Privilege item : collection) {
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
}//TODO role hierarchy
