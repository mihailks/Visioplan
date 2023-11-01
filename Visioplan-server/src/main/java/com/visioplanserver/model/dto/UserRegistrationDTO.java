package com.visioplanserver.model.dto;

public record UserRegistrationDTO(
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
