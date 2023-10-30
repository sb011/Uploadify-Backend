package com.smit.Backend.Models.ResponseModels;

public class LoginResponseModel {
    private String access_token;
    private String name;

    // Getters
    public String getAccess_token() {
        return access_token;
    }

    public String getName() {
        return name;
    }

    // Setters
    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public void setName(String name) {
        this.name = name;
    }

    // constructor
    public LoginResponseModel(String name, String access_token) {
        this.name = name;
        this.access_token = access_token;
    }

    // default constructor
    public LoginResponseModel() {
    }

    @Override
    public String toString() {
        return "LoginResponseModel [name=" + name + "access_token=" + access_token + "]";
    }
}
