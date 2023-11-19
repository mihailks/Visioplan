package com.visioplanserver.model.view.temp;

public class FileViewModelByBuildingName {
    private Long id;
    private String name;
    private String url;

    public FileViewModelByBuildingName() {
    }

    public FileViewModelByBuildingName(Long id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public FileViewModelByBuildingName setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public FileViewModelByBuildingName setName(String name) {
        this.name = name;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public FileViewModelByBuildingName setUrl(String url) {
        this.url = url;
        return this;
    }
}
