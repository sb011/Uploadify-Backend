package com.smit.Backend.Services.Interfaces;

import com.smit.Backend.Models.RequestModels.LoginRequestModel;
import com.smit.Backend.Models.RequestModels.RegisterRequestModel;
import com.smit.Backend.Models.ResponseModels.LoginResponseModel;

public interface IAuthenticationService {
    LoginResponseModel login(LoginRequestModel loginRequest);

    LoginResponseModel register(RegisterRequestModel registerRequest);
}
