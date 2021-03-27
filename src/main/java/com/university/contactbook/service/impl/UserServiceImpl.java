package com.university.contactbook.service.impl;

import com.university.contactbook.entity.User;
import com.university.contactbook.repository.UserRepository;
import com.university.contactbook.service.UserService;
import com.university.contactbook.utils.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository
                .findById(id)
                .get();
//                .orElseThrow(new NotFoundException(format("User with id '%s' not found", id)));
    }

    @Override
    public User createNewUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(Integer id) {
        User user = getUserById(id);
        user.setActive(false);
        user.setDeleted(true);

        userRepository.save(user);
    }
}

