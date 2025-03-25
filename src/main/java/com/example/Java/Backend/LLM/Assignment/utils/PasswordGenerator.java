package com.example.Java.Backend.LLM.Assignment.utils;

import java.security.SecureRandom;
import java.util.Base64;

public class PasswordGenerator {

    public static String generatePassword(String firstName, String dob) {
        String base = firstName.substring(0, 3) + dob.replace("-", "");
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[4]; // Add some randomness
        random.nextBytes(salt);
        return base + Base64.getEncoder().encodeToString(salt).substring(0, 4);
    }
}

