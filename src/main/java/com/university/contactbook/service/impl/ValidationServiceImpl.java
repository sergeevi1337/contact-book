package com.university.contactbook.service.impl;

import com.university.contactbook.entity.Contact;
import com.university.contactbook.entity.User;
import com.university.contactbook.service.ContactService;
import com.university.contactbook.service.ContactValidationService;
import com.university.contactbook.service.UserService;
import com.university.contactbook.service.UserValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Slf4j
@Service
@RequiredArgsConstructor
public class ValidationServiceImpl implements UserValidationService, ContactValidationService {

    private final UserService userService;
    private final ContactService contactService;

    @Override
    public boolean isUniqueUsername(User user, BindingResult bindingResult) {
        boolean exist = userService.isUserExistByUsername(user.getUsername());

        if (exist) {
            User userFromDb = (User) userService.loadUserByUsername(user.getUsername());

            if (!userFromDb.getId().equals(user.getId())) {
                FieldError fieldError = new FieldError("user", "username", "Користувач з таким логіном вже існує");
                bindingResult.addError(fieldError);
            }
        }

        return !exist;
    }

    @Override
    public boolean isUniquePhoneNumber(Contact contact, BindingResult bindingResult) {
        boolean exist = contactService.isContactExistByPhoneNumber(contact.getPhoneNumber());

        if (exist) {
            Contact contactFromDb = contactService.getContactByPhoneNumber(contact.getPhoneNumber());

            if (!contactFromDb.getId().equals(contact.getId())) {
                FieldError fieldError = new FieldError("contact", "phoneNumber", "Контакт з таким номером вже існує");
                bindingResult.addError(fieldError);
            }
        }

        return !exist;
    }
}
