package com.isaguler.springsecuritydemo2.service;

import com.isaguler.springsecuritydemo2.dto.UserDTO;
import com.isaguler.springsecuritydemo2.model.User;
import com.isaguler.springsecuritydemo2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);

    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("user not found")); // TODO: 12.10.2022 handle
    }

    public UserDTO getUserByUsername(String username) {
        User user = this.findUserByUsername(username);

        return UserDTO.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    public void createAdminUser(User user) {
        userRepository.save(user);
    }
}
