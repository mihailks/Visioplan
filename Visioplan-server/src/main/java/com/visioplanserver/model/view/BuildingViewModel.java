package com.visioplanserver.model.view;

import java.util.Set;

public class BuildingViewModel {
    private Long id;
    private String name;
    private String address;
    private String city;
    private String country;
    private String imgUrl;
//    private Set<CompanyNameViewModel> companies;
    private CompanyNameViewModel company;

    private Set<FloorViewModel> floors;

    public BuildingViewModel() {
    }

    public BuildingViewModel(Long id, String name, String address, String city, String country, String imgUrl) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.country = country;
        this.imgUrl = imgUrl;
    }

    public Long getId() {
        return id;
    }

    public BuildingViewModel setId(Long id) {
        this.id = id;
        return this;
    }

//    public Set<CompanyNameViewModel> getCompanies() {
//        return companies;
//    }
//
//    public BuildingViewModel setCompanies(Set<CompanyNameViewModel> companies) {
//        this.companies = companies;
//        return this;
//    }


    public CompanyNameViewModel getCompany() {
        return company;
    }

    public BuildingViewModel setCompany(CompanyNameViewModel company) {
        this.company = company;
        return this;
    }

    public Set<FloorViewModel> getFloors() {
        return floors;
    }

    public BuildingViewModel setFloors(Set<FloorViewModel> floors) {
        this.floors = floors;
        return this;
    }

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


    public String getImgUrl() {
        return imgUrl;
    }

    public BuildingViewModel setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }
}
