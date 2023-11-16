package com.visioplanserver.model.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "floors")
public class FloorEntity extends BaseEntity {
    @Column(name = "number", nullable = false)
    private String number;
    @Column(name = "elevation")
    private String elevation;
    @ManyToOne(cascade = CascadeType.ALL)
    private BuildingEntity building;


    public FloorEntity() {
    }

    public String getNumber() {
        return number;
    }

    public FloorEntity setNumber(String number) {
        this.number = number;
        return this;
    }

    public BuildingEntity getBuilding() {
        return building;
    }

    public FloorEntity setBuilding(BuildingEntity building) {
        this.building = building;
        return this;
    }
}
