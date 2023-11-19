package com.visioplanserver.model.view;

import java.util.Set;

public class FloorViewModel {
    private Long id;
    private String name;
    private String elevation;
    private Set<FileViewModel> filesPerFloor;

    public FloorViewModel() {
    }

    public Long getId() {
        return id;
    }

    public FloorViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public FloorViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getElevation() {
        return elevation;
    }

    public FloorViewModel setElevation(String elevation) {
        this.elevation = elevation;
        return this;
    }

    public Set<FileViewModel> getFilesPerFloor() {
        return filesPerFloor;
    }

    public FloorViewModel setFilesPerFloor(Set<FileViewModel> filesPerFloor) {
        this.filesPerFloor = filesPerFloor;
        return this;
    }
}
