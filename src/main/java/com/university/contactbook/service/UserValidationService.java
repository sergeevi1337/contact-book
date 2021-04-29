package com.university.contactbook.service;

import com.university.contactbook.entity.User;
import org.springframework.validation.BindingResult;

public interface UserValidationService {

    boolean isUniqueUsername(User user, BindingResult bindingResult);
}
