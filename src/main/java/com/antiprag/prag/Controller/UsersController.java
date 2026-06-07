package com.antiprag.prag.Controller;

import com.antiprag.prag.DTO.RefreshTokenRequest;
import com.antiprag.prag.DTO.TokensDTO;
import com.antiprag.prag.DTO.UsersInDTO;
import com.antiprag.prag.DTO.UsersOutDTO;
import com.antiprag.prag.domain.Users;
import com.antiprag.prag.service.JWTService;
import com.antiprag.prag.service.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import java.io.IOException;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    private final JWTService jwtService;

    @GetMapping("/me")
    public ResponseEntity<String> me(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(authentication.getName());
    }

    @GetMapping(path = "/users/{id}")
    public UsersOutDTO getUsers(@PathVariable("id") Integer id) {
        return usersService.getUsers(id);
    }

    @GetMapping(path = "/users")
    public List<UsersOutDTO> ListUsers() {
        return usersService.ListUsers();
    }

    @DeleteMapping(path = "/deletar/Users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarUsers(@PathVariable("id") Integer id) throws IOException {
        usersService.deletarUsers(id);
    }

    @PutMapping(path = "/alterarUsers")
    public void alterarUsers(@RequestBody Users users) throws IOException {
        usersService.alterarUsers(users);
    }

    @PostMapping("/registrar")
    public UsersInDTO register(@RequestBody UsersInDTO users) {
        return usersService.register(users);
    }

    @PostMapping("/logar")
    public TokensDTO login(@RequestBody Users users) {

        return usersService.verify(users);
    }

    @PostMapping("/refreshLogin")
    public TokensDTO refreshLogin(@RequestBody RefreshTokenRequest request) {

        String refreshToken = request.refreshToken();

        if (!jwtService.validateRefreshToken(refreshToken)) {
            throw new RuntimeException("Refresh token inválido");
        }

        String username = jwtService.extractUserName(refreshToken);
        Users user = usersService.findByUsername(username);

        TokensDTO newAccessToken = jwtService.generateTokens(
                user.getUsername(),
                user.getRoles());

        return newAccessToken;
    }

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");

    }
}