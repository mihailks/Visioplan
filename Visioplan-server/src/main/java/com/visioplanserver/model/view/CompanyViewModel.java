package com.visioplanserver.model.view;

import java.util.Set;

public record CompanyViewModel(
        Long id,
        String name,
        String address,
        String city,
        String country,
        String phone,
        String email,
        String website){

    public CompanyViewModel(Long id, String name, String address, String city, String country, String phone, String email, String website) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.country = country;
        this.phone = phone;
        this.email = email;
        this.website = website;
    }
}
