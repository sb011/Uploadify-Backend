package com.smit.Backend.Helpers;

import org.springframework.stereotype.Component;

/**
 * Regex helper class.
 */
@Component
public class RegexHelper {
    /**
     * This method checks if the given string is a valid email.
     * 
     * @param email email
     * @return true if the given string is a valid email, false otherwise
     */
    public static boolean isValidEmail(String email) {
        return email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }
}
