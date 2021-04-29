package com.university.contactbook.utils.exception;

public abstract class ContactBookException extends RuntimeException {

    public ContactBookException(String message) {
        super(message);
    }
}
