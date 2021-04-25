package com.university.contactbook.controller;

import com.university.contactbook.entity.User;
import com.university.contactbook.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("")
    public String openUsersPage(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/add")
    public String openUserCreationPage(User user, Model model) {
        model.addAttribute("user", user);
        return "user-creation-page";
    }

    @PostMapping("/add")
    public String createNewUser(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("While creating new user was detected {} validation errors", bindingResult.getFieldErrorCount());
            return "user-creation-page";
        }

        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        user = userService.createNewUser(user);
        log.debug("New user created: {}", user);

        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUserById(@AuthenticationPrincipal User user, @PathVariable Integer id) {
        if (!user.getId().equals(id)) {
            userService.deleteUserById(id);
            log.debug("User with id '{}' was deleted", id);
        }
        return "redirect:/users";
    }
}
