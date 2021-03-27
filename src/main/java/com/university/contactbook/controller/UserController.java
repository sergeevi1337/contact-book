package com.university.contactbook.controller;

import com.university.contactbook.entity.User;
import com.university.contactbook.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class UserController {

    private final UserService userService;

    public String createNewUser(User user) {
        return "";
    }

    public String deleteUserById(@PathVariable Integer id) {
        return "";
    }
}
