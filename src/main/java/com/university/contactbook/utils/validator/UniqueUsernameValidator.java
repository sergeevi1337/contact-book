package com.university.contactbook.utils.validator;

import com.university.contactbook.entity.User;
import com.university.contactbook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsernameConstraint, String> {

    private String message;
    private final UserRepository userRepository;

    @Override
    public void initialize(UniqueUsernameConstraint constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        Optional<User> optional = userRepository.findByUsernameAndDeletedFalse(username);
        boolean valid = optional.isEmpty();

        if (valid) {
            constraintValidatorContext.buildConstraintViolationWithTemplate(message)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }

        return valid;
    }
}
