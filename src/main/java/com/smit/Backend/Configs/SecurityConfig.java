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

@Configuration
public class SecurityConfig {
    private final JWTHelper jwtHelper;
    private final IUserRepository userRepository;

    @Autowired
    public SecurityConfig(JWTHelper jwtHelper, IUserRepository userRepository) {
        this.jwtHelper = jwtHelper;
        this.userRepository = userRepository;
    }

    @Bean
    public FilterRegistrationBean<TokenFilter> TokenFilter() {
        FilterRegistrationBean<TokenFilter> registrationBean = new FilterRegistrationBean<>();
        TokenFilter tokenFilter = new TokenFilter(jwtHelper, userRepository);
        registrationBean.setFilter(tokenFilter);
        registrationBean.addUrlPatterns("/api/*");
        return registrationBean;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
