package com.smit.Backend.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.smit.Backend.Execptions.BadRequestException;
import com.smit.Backend.Helpers.JWTHelper;
import com.smit.Backend.Helpers.RegexHelper;
import com.smit.Backend.Models.EntityModels.UserModel;
import com.smit.Backend.Models.RequestModels.LoginRequestModel;
import com.smit.Backend.Models.RequestModels.RegisterRequestModel;
import com.smit.Backend.Models.ResponseModels.LoginResponseModel;
import com.smit.Backend.Repositories.Interfaces.IUserRepository;
import com.smit.Backend.Services.Interfaces.IAuthenticationService;

/**
 * Authentication service class.
 */
@Service
public class AuthenticationService implements IAuthenticationService {
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTHelper jwtHelper;

    /**
     * Constructor.
     * 
     * @param userRepository  user repository
     * @param passwordEncoder password encoder
     * @param jwtHelper       JWT helper
     */
    @Autowired
    public AuthenticationService(IUserRepository userRepository,
            PasswordEncoder passwordEncoder, JWTHelper jwtHelper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtHelper = jwtHelper;
    }

    /**
     * This method handles login requests.
     * 
     * @param loginRequest login request
     * @return login response
     */
    @Override
    public LoginResponseModel login(LoginRequestModel loginRequest) {
        // validate request
        if (loginRequest.getEmail() == null || loginRequest.getEmail().isBlank())
            throw new BadRequestException("Email cannot be empty.");

        if (RegexHelper.isValidEmail(loginRequest.getEmail()) == false)
            throw new BadRequestException("Email is not valid.");

        if (loginRequest.getPassword() == null || loginRequest.getPassword().isBlank())
            throw new BadRequestException("Password cannot be empty.");

        var userDB = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(
                () -> new BadRequestException("User with email " + loginRequest.getEmail() + " does not exist."));

        // validate password
        if (!passwordEncoder.matches(loginRequest.getPassword(), userDB.getPassword()))
            throw new BadRequestException("Invalid password.");

        // generate the access token
        var accessToken = jwtHelper.generateToken(userDB.getId());

        return new LoginResponseModel(accessToken);
    }

    /**
     * This method handles register requests.
     * 
     * @param registerRequest register request
     * @return login response
     */
    @Override
    public LoginResponseModel register(RegisterRequestModel registerRequest) {
        // validate request
        if (registerRequest.getName() == null || registerRequest.getName().isBlank())
            throw new BadRequestException("Name cannot be empty.");

        if (registerRequest.getEmail() == null || registerRequest.getEmail().isBlank())
            throw new BadRequestException("Email cannot be empty.");

        if (registerRequest.getPassword() == null || registerRequest.getPassword().isBlank())
            throw new BadRequestException("Password cannot be empty.");

        if (RegexHelper.isValidEmail(registerRequest.getEmail()) == false)
            throw new BadRequestException("Email is not valid.");

        var isUser = userRepository.findByEmail(registerRequest.getEmail());
        if (isUser.isPresent())
            throw new BadRequestException("User with email " + registerRequest.getEmail() + " already exists.");

        // hash the password
        var hashedPassword = passwordEncoder.encode(registerRequest.getPassword());

        var user = new UserModel(registerRequest.getName(), registerRequest.getEmail(),
                hashedPassword);
        userRepository.save(user);

        // generate the access token
        var accessToken = jwtHelper.generateToken(user.getId());
        return new LoginResponseModel(accessToken);
    }
}
