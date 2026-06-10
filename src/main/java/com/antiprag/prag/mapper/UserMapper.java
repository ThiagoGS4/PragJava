package com.antiprag.prag.mapper;

import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.antiprag.prag.DTO.UsersInDTO;
import com.antiprag.prag.DTO.UsersOutDTO;
import com.antiprag.prag.domain.Roles;
import com.antiprag.prag.domain.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Component
public class UserMapper {
    
    @PersistenceContext
    private EntityManager entityManager;

    // dto para output de dados do(s) usuário(s)
    public UsersOutDTO usersToOutDto(Users user) {
        Set<String> roles = user.getRoles()
            .stream()
            .map(Roles::getName)
            .collect(Collectors.toSet());

        return new UsersOutDTO(
                user.getId(),
                user.getUsername(),
                user.getIsActive(),
                user.getCreatedAt(),
                roles
        );
    }

    // dto para input de usuário
    public Users usersToEntity(UsersInDTO dto) {
        Set<Roles> roles = dto.roles()
            .stream()
            .map(id -> entityManager.getReference(Roles.class, id))
            .collect(Collectors.toSet());

        Users user = new Users();
        user.setUsername(dto.username());
        user.setPassword(dto.password());
        user.setIsActive(dto.is_active());
        user.setRoles(roles);

        return user;
    }
}
