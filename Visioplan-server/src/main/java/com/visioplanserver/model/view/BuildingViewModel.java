package com.visioplanserver.model.view;

public class BuildingViewModel {
    private String name;
    private String address;
    private String city;
    private String country;
    private String imgUrl;

    public String getName() {
        return name;
    }

    public BuildingViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public BuildingViewModel setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getCity() {
        return city;
    }

    public BuildingViewModel setCity(String city) {
        this.city = city;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public BuildingViewModel setCountry(String country) {
        this.country = country;
        return this;
    }

    public BuildingViewModel() {
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public BuildingViewModel setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }
}