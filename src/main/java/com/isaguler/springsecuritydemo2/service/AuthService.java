package com.isaguler.springsecuritydemo2.service;

import com.isaguler.springsecuritydemo2.dto.LoginRequest;
import com.isaguler.springsecuritydemo2.dto.LoginResponse;
import com.isaguler.springsecuritydemo2.dto.RegisterRequest;
import com.isaguler.springsecuritydemo2.dto.UserDTO;
import com.isaguler.springsecuritydemo2.enums.Role;
import com.isaguler.springsecuritydemo2.model.User;
import com.isaguler.springsecuritydemo2.utils.JWTTokenGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final JWTTokenGenerator jwtTokenGenerator;
    private final AuthenticationManager authenticationManager;
    
    public UserDTO register(RegisterRequest registerRequest) {

        User user = User.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .email(registerRequest.getEmail())
                .role(Role.ROLE_USER)
                .build();

        User savedUser = userService.saveUser(user);

        return UserDTO.builder()
                .username(savedUser.getUsername())
                .role(savedUser.getRole())
                .email(savedUser.getEmail())
                .build();
    }

    public LoginResponse login(LoginRequest loginRequest) {
        try {
            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            return LoginResponse.builder()
                    .accessToken(jwtTokenGenerator.generateJWTToken(authentication))
                    .userDTO(userService.getUserByUsername(loginRequest.getUsername()))
                    .build();

        } catch (Exception e) {
            throw new RuntimeException("login exception");
        }

    }


}
