package com.university.contactbook.controller;

import com.university.contactbook.entity.Contact;
import com.university.contactbook.service.ContactService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/contacts")
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
public class ContactController {

    private final ContactService contactService;

    public String getAllContacts() {
        return "";
    }

    public String openContactCreationPage() {
        return "";
    }

    public String createNewContact(Contact contact) {
        return "";
    }

    public String openContactEditingPageById(@PathVariable Integer id) {
        return "";
    }

    public String editContact(Contact contact) {
        return "";
    }

    public String deleteContactById(@PathVariable Integer id) {
        return "";
    }
}
