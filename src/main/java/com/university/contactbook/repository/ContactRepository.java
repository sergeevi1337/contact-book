package com.university.contactbook.repository;

import com.university.contactbook.entity.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Integer> {

    List<Contact> findAllByDeletedFalse();

    boolean existsByPhoneNumberAndDeletedFalse(String phoneNumber);

    Optional<Contact> findByPhoneNumberAndDeletedFalse(String phoneNumber);
}
