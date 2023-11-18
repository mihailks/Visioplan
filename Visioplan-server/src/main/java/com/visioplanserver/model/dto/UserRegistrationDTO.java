package com.visioplanserver.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRegistrationDTO(
        @NotNull
                @NotBlank
        String username,
        String email,
        String password,
        String confirmPassword,
        String firstName,
        String lastName,
        String companyName){


    public static UserRegistrationDTO createEmpty(){
        return new UserRegistrationDTO(
                null, null, null, null, null, null, null);
        }
}
