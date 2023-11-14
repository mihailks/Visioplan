package com.visioplanserver.model.dto;

public record UserProfileEditDTO(
        Long id,
        String username,
        String email,
        String firstName,
        String lastName) {


    public static UserProfileEditDTO createEmpty() {
        return new UserProfileEditDTO(
                null,
                null,
                null,
                null,
                null);
    }
}
