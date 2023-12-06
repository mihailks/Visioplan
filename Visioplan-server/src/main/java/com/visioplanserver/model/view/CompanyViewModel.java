package com.visioplanserver.model.view;

import java.util.Objects;
import java.util.Set;

public final class CompanyViewModel {
    private Long id;
    private String name;
    private String address;
    private String city;
    private String country;
    private String phone;
    private String email;
    private String website;


    public CompanyViewModel() {
    }

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

    public Long getId() {
        return id;
    }

    public CompanyViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CompanyViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public CompanyViewModel setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getCity() {
        return city;
    }

    public CompanyViewModel setCity(String city) {
        this.city = city;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public CompanyViewModel setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public CompanyViewModel setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CompanyViewModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getWebsite() {
        return website;
    }

    public CompanyViewModel setWebsite(String website) {
        this.website = website;
        return this;
    }
}
