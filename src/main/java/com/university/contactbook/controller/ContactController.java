package com.university.contactbook.controller;

import com.university.contactbook.entity.Contact;
import com.university.contactbook.service.ContactService;
import com.university.contactbook.utils.CKEditorResultHtmlParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
public class ContactController {

    private final ContactService contactService;

    @GetMapping("/")
    public String openContactsPage() {
        return "redirect:/contacts";
    }

    @GetMapping("/contacts")
    public String openContactsPage(Model model) {
        List<Contact> contacts = contactService.getAllContacts();
        model.addAttribute("contacts", contacts);
        model.addAttribute("parser", CKEditorResultHtmlParser.getInstance());
        return "contacts";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/contacts/add")
    public String openContactCreationPage(Contact contact, Model model) {
        model.addAttribute("contact", contact);
        return "contact-creation-page";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/contacts/add")
    public String createNewContact(@Valid Contact contact, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("While creating new contact was detected {} validation errors", bindingResult.getFieldErrorCount());
            return "contact-creation-page";
        }

        contact = contactService.createNewContact(contact);
        log.debug("New contact created: {}", contact);

        return "redirect:/contacts";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/contacts/edit/{id}")
    public String openContactEditingPageById(@PathVariable Integer id, Model model) {
        Contact contact = contactService.getContactById(id);
        model.addAttribute("contact", contact);
        return "contact-editing-page";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/contacts/edit/{id}")
    public String editContact(@Valid Contact contact, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("While editing contact was detected {} validation errors", bindingResult.getFieldErrorCount());
            return "contact-editing-page";
        }

        contact = contactService.editContact(contact);
        log.debug("Contact successfully edited: {}", contact);

        return "redirect:/contacts";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/contacts/delete/{id}")
    public String deleteContactById(@PathVariable Integer id) {
        contactService.deleteContactById(id);
        log.debug("Contact with id '{}' was deleted", id);
        return "redirect:/contacts";
    }

    @GetMapping("/print")
    public String print() {
        return "contacts";
    }
}
