package com.visioplanserver.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "floors")
public class FloorEntity extends BaseEntity {
    @Column(name = "floor_number", nullable = false, unique = true)
    private String number;
    @ManyToOne
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
