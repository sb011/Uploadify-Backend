package com.smit.Backend.Configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.smit.Backend.Helpers.JWTHelper;
import com.smit.Backend.Filters.TokenFilter;
import com.smit.Backend.Repositories.Interfaces.IUserRepository;

/**
 * Security configuration class.
 */
@Configuration
public class SecurityConfig {
    private final JWTHelper jwtHelper;
    private final IUserRepository userRepository;

    /**
     * Constructor.
     * 
     * @param jwtHelper      JWT helper
     * @param userRepository user repository
     */
    @Autowired
    public SecurityConfig(JWTHelper jwtHelper, IUserRepository userRepository) {
        this.jwtHelper = jwtHelper;
        this.userRepository = userRepository;
    }

    /**
     * This method creates a TokenFilter object.
     * 
     * @return TokenFilter object
     */
    @Bean
    public FilterRegistrationBean<TokenFilter> TokenFilter() {
        FilterRegistrationBean<TokenFilter> registrationBean = new FilterRegistrationBean<>();
        TokenFilter tokenFilter = new TokenFilter(jwtHelper, userRepository);
        registrationBean.setFilter(tokenFilter);
        registrationBean.addUrlPatterns("/api/*");
        return registrationBean;
    }

    /**
     * This method creates a PasswordEncoder object.
     * 
     * @return PasswordEncoder object
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
