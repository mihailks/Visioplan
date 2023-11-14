package com.visioplanserver.model.view;

public class CompanyNameViewModel {
    String id;
    String name;

    public CompanyNameViewModel() {
    }

    public String getId() {
        return id;
    }

    public CompanyNameViewModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CompanyNameViewModel setName(String name) {
        this.name = name;
        return this;
    }
}
