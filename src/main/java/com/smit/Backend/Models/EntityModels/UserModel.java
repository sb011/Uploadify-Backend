package com.smit.Backend.Models.EntityModels;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * User model class.
 */
public class UserModel {
    private String id;
    private String name;
    private String email;
    private String password;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    // getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    // setters
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String hashedPassword) {
        this.password = hashedPassword;
    }

    // constructor
    public UserModel(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // default constructor
    public UserModel() {
    }

    @Override
    public String toString() {
        return "UserModel [createdAt=" + createdAt + ", email=" + email + ", id=" + id + ", name=" + name
                + ", password=" + password + ", updatedAt=" + updatedAt + "]";
    }
}
