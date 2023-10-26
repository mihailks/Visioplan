package com.visioplanserver.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Set;
@Entity
@Table(name = "buildings")
public class BuildingEntity extends BaseEntity{
    private String name;
    private String address;
    private String city;
    private String country;
    @ManyToMany
    private Set<CompanyEntity> companies;
    @OneToMany(mappedBy = "building")
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
}
