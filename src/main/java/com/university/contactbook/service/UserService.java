package com.university.contactbook.service;

import com.university.contactbook.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getAllUsers();

    User getUserById(Integer id);

    User createNewUser(User user);

    void deleteUserById(Integer id);
}
