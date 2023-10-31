package com.smit.Backend.Models.RequestModels;

/**
 * Register request model class.
 */
public class RegisterRequestModel {
    private String name;
    private String email;
    private String password;

    // Getters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // constructor
    public RegisterRequestModel(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public RegisterRequestModel() {
    }

    // toString
    @Override
    public String toString() {
        return "RegisterRequestModel [email=" + email + ", name=" + name + ", password=" + password + "]";
    }
}
