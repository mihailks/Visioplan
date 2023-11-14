package com.visioplanserver.model.dto;

public class UserProfileEditDTO {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;

    public UserProfileEditDTO() {
    }

    public Long getId() {
        return id;
    }

    public UserProfileEditDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserProfileEditDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserProfileEditDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserProfileEditDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserProfileEditDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
