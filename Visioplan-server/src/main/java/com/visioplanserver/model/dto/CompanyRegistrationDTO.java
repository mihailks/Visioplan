package com.visioplanserver.model.dto;

public record CompanyRegistrationDTO(
        String name,
        String address,
        String city,
        String country,
        String phone,
        String email,
        String website) {

    public static CompanyRegistrationDTO createEmpty(){
        return new CompanyRegistrationDTO(
                null, null, null, null, null, null, null);
    }

}
