package com.visioplanserver.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "employees")
public class EmployeeEntity extends UserEntity {
    @ManyToOne
    private CompanyEntity company;

}
