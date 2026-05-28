package com.antiprag.prag.service;

import java.util.List;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.antiprag.prag.domain.Users;
import com.antiprag.prag.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;
    
    private final JWTService jwtService;

    private final AuthenticationManager authManager;

    public Users getUsers(Integer id) {
        return usersRepository.findById(id).orElse(null);
    }

    public List<Users> ListUsers() {
        return usersRepository.findAll();
    }

    public void deletarUsers(int idUsers) {
        usersRepository.deleteById(idUsers);
    }

    public void alterarUsers(Users Users) {
        usersRepository.save(Users);
    }

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    
    public void inserirUsers(Users users) {
        users.setPassword(encoder.encode(users.getPassword()));
        usersRepository.save(users);
    }
    
    public Users register(Users users) {
        users.setPassword(encoder.encode(users.getPassword()));
        usersRepository.save(users);
        return users;
    }

    public String verify(Users users) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(users.getUsername(), users.getPassword()));
   if (authentication.isAuthenticated()) {
         return jwtService.generateToken(users.getUsername());
        } else {
            return "Falha";
        }
    }

}