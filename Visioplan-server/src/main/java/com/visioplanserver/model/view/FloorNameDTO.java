package com.visioplanserver.model.view;

public class FloorNameDTO {
    private String name;

    public FloorNameDTO() {
    }

    public FloorNameDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public FloorNameDTO setName(String name) {
        this.name = name;
        return this;
    }
}
