package com.visioplanserver.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CompanyRegistrationDTO(
        @NotBlank(message = "Name cannot be empty")
        @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
        String name,
        @NotBlank(message = "Address cannot be empty")
        @Size(min = 3, max = 200, message = "Address must be between 3 and 200 characters")
        String address,
        @NotBlank(message = "City cannot be empty")
        @Size(min = 3, max = 100, message = "City must be between 3 and 100 characters")
        String city,
        @NotBlank(message = "Country cannot be empty")
        @Size(min = 3, max = 100, message = "Country must be between 3 and 100 characters")
        String country,
        String phone,

        @NotBlank(message = "Email cannot be empty")
        @Email(message = "Email must be valid")
        @Size(min = 3, max = 100, message = "Email must be between 3 and 100 characters")
        String email,

        String website) {

    public static CompanyRegistrationDTO createEmpty() {
        return new CompanyRegistrationDTO(
                null, null, null, null, null, null, null);
    }

}
