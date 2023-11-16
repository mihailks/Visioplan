package com.visioplanserver.model.entity;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String username;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @ManyToOne
    private CompanyEntity company;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns =  @JoinColumn(name = "role_id"))
    private List<UserRoleEntity> role;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<CommentsEntity> comments;

    public UserEntity() {
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }



    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public List<UserRoleEntity> getRole() {
        return role;
    }

    public UserEntity setRole(List<UserRoleEntity> role) {
        this.role = role;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public CompanyEntity getCompany() {
        return company;
    }

    public UserEntity setCompany(CompanyEntity company) {
        this.company = company;
        return this;
    }

    public List<CommentsEntity> getComments() {
        return comments;
    }

    public UserEntity setComments(List<CommentsEntity> comments) {
        this.comments = comments;
        return this;
    }
}
