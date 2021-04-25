package com.university.contactbook.service.impl;

import com.university.contactbook.entity.Contact;
import com.university.contactbook.repository.ContactRepository;
import com.university.contactbook.service.ContactService;
import com.university.contactbook.utils.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    @Override
    public List<Contact> getAllContacts() {
        return contactRepository.findAllByDeletedFalse();
    }

    @Override
    public Contact getContactById(Integer id) {
        return contactRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(format("Contact with id '%s' not found", id)));
    }

    @Override
    public Contact createNewContact(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public Contact editContact(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public void deleteContactById(Integer id) {
        Contact contact = getContactById(id);
        contact.setDeleted(true);

        contactRepository.save(contact);
    }
}
