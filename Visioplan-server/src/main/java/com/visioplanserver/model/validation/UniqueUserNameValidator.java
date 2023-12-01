package com.visioplanserver.model.validation;

import com.visioplanserver.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueUserNameValidator implements ConstraintValidator<UniqueUsername, String> {
   private final UserService userService;

    public UniqueUserNameValidator(UserService userService) {
        this.userService = userService;
    }
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return userService
                .findByUsername(value)
                .isEmpty();
    }
}
