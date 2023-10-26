package com.visioplanserver.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "companies")
public class CompanyEntity extends BaseEntity {
    private String name;
    private String address;
    private String city;
    private String country;
    private String phone;
    private String email;
    private String website;
    @ManyToMany(mappedBy = "companies")
    private Set<BuildingEntity> buildings;
    @OneToMany(mappedBy = "company")
    private Set<EmployeeEntity> employees;

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


    public Set<EmployeeEntity> getEmployees() {
        return employees;
    }

    public CompanyEntity setEmployees(Set<EmployeeEntity> employees) {
        this.employees = employees;
        return this;
    }
}
