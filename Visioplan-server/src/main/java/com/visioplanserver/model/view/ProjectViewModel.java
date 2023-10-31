package com.visioplanserver.model.view;

public class ProjectViewModel {
    private String name;
    private String address;
    private String city;
    private String country;

    public String getName() {
        return name;
    }

    public ProjectViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public ProjectViewModel setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getCity() {
        return city;
    }

    public ProjectViewModel setCity(String city) {
        this.city = city;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public ProjectViewModel setCountry(String country) {
        this.country = country;
        return this;
    }

    public ProjectViewModel() {


    }
}
