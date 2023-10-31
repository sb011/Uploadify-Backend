package com.smit.Backend.Models.ResponseModels;

public class LoginResponseModel {
    private String access_token;

    // Getters
    public String getAccess_token() {
        return access_token;
    }

    // Setters
    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    // constructor
    public LoginResponseModel(String access_token) {
        this.access_token = access_token;
    }

    // default constructor
    public LoginResponseModel() {
    }

    @Override
    public String toString() {
        return "LoginResponseModel [access_token=" + access_token + "]";
    }
}
