package com.visioplanserver.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class EmployeeEntity extends BaseEntity {
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @OneToOne
    private UserEntity user;
    @ManyToOne
    private CompanyEntity company;

    public EmployeeEntity() {
    }

    public CompanyEntity getCompany() {
        return company;
    }

    public EmployeeEntity setCompany(CompanyEntity company) {
        this.company = company;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public EmployeeEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public EmployeeEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public EmployeeEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }
}
