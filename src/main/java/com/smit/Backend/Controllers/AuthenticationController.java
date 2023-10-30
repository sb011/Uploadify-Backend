package com.smit.Backend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smit.Backend.Models.RequestModels.LoginRequestModel;
import com.smit.Backend.Models.RequestModels.RegisterRequestModel;
import com.smit.Backend.Models.ResponseModels.LoginResponseModel;
import com.smit.Backend.Services.Interfaces.IAuthenticationService;

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {
    private final IAuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(IAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public LoginResponseModel login(@RequestBody LoginRequestModel loginRequest) {
        return authenticationService.login(loginRequest);
    }

    @PostMapping("/register")
    public LoginResponseModel register(@RequestBody RegisterRequestModel registerRequest) {
        return authenticationService.register(registerRequest);
    }
}
