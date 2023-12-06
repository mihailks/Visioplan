package com.visioplanserver.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public record AddBuildingDTO(
        @NotBlank(message = "Name cannot be empty")
        @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters long")
        String name,
        @NotBlank(message = "Address cannot be empty")
        @Size(min = 3, max = 200, message = "Address must be between 3 and 200 characters long")
        String address,
        @NotBlank(message = "City cannot be empty")
        @Size(min = 3, max = 100, message = "City must be between 3 and 100 characters long")
        String city,
        @NotBlank(message = "Country cannot be empty")
        @Size(min = 3, max = 100, message = "Country must be between 3 and 100 characters long")
        String country,

        MultipartFile imageFile) {

    public static AddBuildingDTO createEmpty() {
        return new AddBuildingDTO(
                null,
                null,
                null,
                null,
                null);
    }

}
