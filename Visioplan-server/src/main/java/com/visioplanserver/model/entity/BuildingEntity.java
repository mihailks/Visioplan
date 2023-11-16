package com.visioplanserver.model.entity;

import jakarta.persistence.*;

import java.util.Set;
@Entity
@Table(name = "buildings")
public class BuildingEntity extends BaseEntity{
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String country;
    private String imgUrl;
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<CompanyEntity> companies;
    @OneToMany(mappedBy = "building", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<FloorEntity> floors;

    public BuildingEntity() {
    }

    public String getName() {
        return name;
    }

    public BuildingEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public BuildingEntity setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getCity() {
        return city;
    }

    public BuildingEntity setCity(String city) {
        this.city = city;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public BuildingEntity setCountry(String country) {
        this.country = country;
        return this;
    }

    public Set<FloorEntity> getFloors() {
        return floors;
    }

    public BuildingEntity setFloors(Set<FloorEntity> floors) {
        this.floors = floors;
        return this;
    }

    public Set<CompanyEntity> getCompanies() {
        return companies;
    }

    public BuildingEntity setCompanies(Set<CompanyEntity> companies) {
        this.companies = companies;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public BuildingEntity setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }
}
