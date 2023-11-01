package com.visioplanserver.model.dto;

import org.springframework.web.multipart.MultipartFile;

public record AddBuildingDTO(
        String name,
        String address,
        String city,
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
