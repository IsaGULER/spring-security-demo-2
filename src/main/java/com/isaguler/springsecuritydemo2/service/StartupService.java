package com.isaguler.springsecuritydemo2.service;

import com.isaguler.springsecuritydemo2.enums.Role;
import com.isaguler.springsecuritydemo2.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StartupService implements ApplicationListener<ApplicationReadyEvent> {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        userService.createAdminUser(User.builder()
                .username("admin1")
                .password(passwordEncoder.encode("adminpass1"))
                .email("email1@gmail.com")
                .role(Role.ROLE_ADMIN)
                .build());
    }
}
