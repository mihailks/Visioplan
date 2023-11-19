package com.visioplanserver.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CompanyRegistrationDTO(
        @NotNull
        @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
        String name,
        @NotNull
        @Size(min = 3, max = 200, message = "Address must be between 3 and 200 characters")
        String address,
        @NotNull
        @Size(min = 3, max = 100, message = "City must be between 3 and 100 characters")
        String city,
        @NotNull
        @Size(min = 3, max = 100, message = "Country must be between 3 and 100 characters")
        String country,
        String phone,
        @NotNull
        @Email(message = "Email must be valid")
        String email,
        String website) {

    public static CompanyRegistrationDTO createEmpty() {
        return new CompanyRegistrationDTO(
                null, null, null, null, null, null, null);
    }

}
