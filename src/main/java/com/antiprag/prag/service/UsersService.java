package com.antiprag.prag.service;

import java.util.List;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.antiprag.prag.DTO.UsersInDTO;
import com.antiprag.prag.DTO.UsersOutDTO;
import com.antiprag.prag.domain.Users;
import com.antiprag.prag.mapper.UserMapper;
import com.antiprag.prag.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;
    
    private final JWTService jwtService;

    private final AuthenticationManager authManager;

    private final UserMapper userMapper;

    public UsersOutDTO getUsers(Integer id) {
        Users user = usersRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));

        return userMapper.usersToOutDto(user);
    }

    public List<UsersOutDTO> ListUsers() {
        return usersRepository.findAll()
            .stream()
            .map(userMapper::usersToOutDto)
            .toList();
    }

    public void deletarUsers(int idUsers) {
        usersRepository.deleteById(idUsers);
    }

    public void alterarUsers(Users Users) {
        usersRepository.save(Users);
    }

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    
    public UsersInDTO register(UsersInDTO users) {

        if(users.roles().isEmpty()){
            users.roles().add(2);
        }

        Users usersEntity = userMapper.usersToEntity(users);
        usersEntity.setPassword(encoder.encode(usersEntity.getPassword()));
        usersRepository.save(usersEntity);
        return users;
    }

    public String verify(Users users) { // TODO criar DTO para login
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(users.getUsername(), users.getPassword()));
   if (authentication.isAuthenticated()) {
         return jwtService.generateToken(users.getUsername());
        } else {
            return "Falha";
        }
    }

}