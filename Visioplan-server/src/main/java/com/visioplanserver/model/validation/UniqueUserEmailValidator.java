package com.visioplanserver.model.validation;

import com.visioplanserver.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueUserEmailValidator implements ConstraintValidator<UniqueUserEmail, String> {
    private final UserService userService;

    public UniqueUserEmailValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return userService
                .findUserByEmail(value)
                .isEmpty();
    }
}
