package com.visioplanserver.model.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "companies")
public class CompanyEntity extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String country;
    private String phone;
    @Column(nullable = false, unique = true)
    private String email;
    private String website;
    @ManyToMany(mappedBy = "companies")
    private Set<BuildingEntity> buildings;
    @OneToMany(mappedBy = "company")
    private Set<UserEntity> employees;

    public CompanyEntity() {
    }

    public String getName() {
        return name;
    }

    public CompanyEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public CompanyEntity setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getCity() {
        return city;
    }

    public CompanyEntity setCity(String city) {
        this.city = city;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public CompanyEntity setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public CompanyEntity setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CompanyEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getWebsite() {
        return website;
    }

    public CompanyEntity setWebsite(String website) {
        this.website = website;
        return this;
    }

    public Set<BuildingEntity> getBuildings() {
        return buildings;
    }

    public CompanyEntity setBuildings(Set<BuildingEntity> buildings) {
        this.buildings = buildings;
        return this;
    }


    public Set<UserEntity> getEmployees() {
        return employees;
    }

    public CompanyEntity setEmployees(Set<UserEntity> employees) {
        this.employees = employees;
        return this;
    }
}
