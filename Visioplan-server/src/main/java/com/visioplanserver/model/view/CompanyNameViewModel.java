package com.visioplanserver.model.view;

public class CompanyNameViewModel {
    Long id;
    String name;

    public CompanyNameViewModel() {
    }

    public CompanyNameViewModel(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public CompanyNameViewModel setId(Long id) {
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
