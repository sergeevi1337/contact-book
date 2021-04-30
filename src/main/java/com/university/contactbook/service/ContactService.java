package com.university.contactbook.service;

import com.university.contactbook.entity.Contact;

import java.util.List;

public interface ContactService {

    List<Contact> getAllContacts();

    Contact getContactById(Integer id);

    Contact createNewContact(Contact contact);

    Contact editContact(Contact contact);

    void deleteContactById(Integer id);

    boolean isContactExistByPhoneNumber(String phoneNumber);

    Contact getContactByPhoneNumber(String phoneNumber);
}
