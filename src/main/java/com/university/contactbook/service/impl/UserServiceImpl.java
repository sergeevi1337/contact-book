package com.university.contactbook.service.impl;

import com.university.contactbook.entity.Role;
import com.university.contactbook.entity.User;
import com.university.contactbook.repository.UserRepository;
import com.university.contactbook.service.UserService;
import com.university.contactbook.utils.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static java.lang.String.format;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAllByDeletedFalse();
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(format("User with id '%s' not found", id)));
    }

    @Override
    public User createNewUser(User user) {
        user.setRoles(Collections.singleton(Role.USER));
        user.setActive(true);
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(Integer id) {
        User user = getUserById(id);
        user.setActive(false);
        user.setDeleted(true);

        userRepository.save(user);
    }

    @Override
    public boolean isUserExistByUsername(String username) {
        return userRepository.existsByUsernameAndDeletedFalse(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Retrieving user by username '{}'", username);
        return userRepository.findByUsernameAndDeletedFalse(username)
                .orElseThrow(() -> new NotFoundException(format("User with username '%s' not found", username)));
    }
}

