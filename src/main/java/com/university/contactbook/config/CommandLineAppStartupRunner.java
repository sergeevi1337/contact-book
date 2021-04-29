package com.university.contactbook.config;

import com.university.contactbook.entity.Role;
import com.university.contactbook.entity.User;
import com.university.contactbook.repository.UserRepository;
import com.university.contactbook.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Slf4j
@Component
@AllArgsConstructor
public class CommandLineAppStartupRunner implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        User defaultAdmin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .roles(Collections.singleton(Role.ADMIN))
                .active(true)
                .build();

        boolean exist = userRepository.existsByUsernameAndDeletedFalse(defaultAdmin.getUsername());

        if (!exist) {
            defaultAdmin = userRepository.save(defaultAdmin);
            log.debug("Initialized default admin user = {}", defaultAdmin);
        }
    }
}

