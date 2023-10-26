package com.visioplanserver.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "floors")
public class FloorEntity extends BaseEntity {
    private String name;
    @ManyToOne
    private BuildingEntity building;

}
