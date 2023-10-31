package com.visioplanserver.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "floors")
public class FloorEntity extends BaseEntity {
    @Column(name = "floor_name", nullable = false, unique = true)
    private String name;
    @ManyToOne
    private BuildingEntity building;


    public FloorEntity() {
    }

    public String getName() {
        return name;
    }

    public FloorEntity setName(String name) {
        this.name = name;
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