package com.university.contactbook.utils.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class ContactBookExceptionHandler {

    @ExceptionHandler(ContactBookException.class)
    public ModelAndView handleContactBookExceptions(ContactBookException e) {
        log.error("Error was handled", e);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("errorMessage", e.getMessage());
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
