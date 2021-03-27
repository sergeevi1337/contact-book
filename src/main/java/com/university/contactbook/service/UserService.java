package com.university.contactbook.service;

import com.university.contactbook.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Integer id);

    User createNewUser(User user);

    void deleteUserById(Integer id);
}
