package com.smit.Backend.Helpers;

import org.springframework.stereotype.Component;

@Component
public class RegexHelper {
    public static boolean isValidEmail(String email) {
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }
}
