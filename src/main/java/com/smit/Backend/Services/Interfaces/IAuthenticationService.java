package com.smit.Backend.Services.Interfaces;

import com.smit.Backend.Models.RequestModels.LoginRequestModel;
import com.smit.Backend.Models.RequestModels.RegisterRequestModel;
import com.smit.Backend.Models.ResponseModels.LoginResponseModel;

/**
 * Authentication service interface.
 */
public interface IAuthenticationService {
    /**
     * This method handles login requests.
     * 
     * @param loginRequest login request
     * @return login response
     */
    LoginResponseModel login(LoginRequestModel loginRequest);

    /**
     * This method handles register requests.
     * 
     * @param registerRequest register request
     * @return login response
     */
    LoginResponseModel register(RegisterRequestModel registerRequest);
}
