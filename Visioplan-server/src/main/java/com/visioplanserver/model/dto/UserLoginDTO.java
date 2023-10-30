package com.visioplanserver.model.dto;

public record UserLoginDTO(
        String username,
        String password
) {

    public static UserLoginDTO createEmpty() {
        return new UserLoginDTO(
                null, null);
    }
}
