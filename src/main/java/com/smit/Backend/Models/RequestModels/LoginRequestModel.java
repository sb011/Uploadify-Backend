package com.smit.Backend.Models.RequestModels;

public class LoginRequestModel {
    private String email;
    private String password;

    // Getters
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // constructor
    public LoginRequestModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // default constructor
    public LoginRequestModel() {
    }

    @Override
    public String toString() {
        return "LoginRequestModel [email=" + email + ", password=" + password + "]";
    }
}
