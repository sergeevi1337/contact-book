package com.university.contactbook.service;

import com.university.contactbook.entity.Contact;
import org.springframework.validation.BindingResult;

public interface ContactValidationService {

    boolean isUniquePhoneNumber(Contact contact, BindingResult bindingResult);
}
