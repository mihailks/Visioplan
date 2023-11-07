package com.visioplanserver.model.view;


import java.util.ArrayList;
import java.util.List;

public class BuildingNameDTO {
    private String name;
    private List<String> floors = new ArrayList<>();

    public BuildingNameDTO() {
    }

    public String getName() {
        return name;
    }

    public BuildingNameDTO setName(String name) {
        this.name = name;
        return this;
    }

    public List<String> getFloors() {
        return floors;
    }

    public BuildingNameDTO setFloors(List<String> floors) {
        this.floors = floors;
        return this;
    }
}
