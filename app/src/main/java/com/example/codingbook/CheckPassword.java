package com.example.codingbook;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckPassword {
    public static boolean isStrongPassword(String password) {
        // Check if the password has a minimum length of 8 characters
        if (password.length() < 8) {
            return false;
        }

        // Check if the password contains at least one uppercase letter
        if (!containsUppercase(password)) {
            return false;
        }

        // Check if the password contains at least one lowercase letter
        if (!containsLowercase(password)) {
            return false;
        }

        // Check if the password contains at least one digit
        if (!containsDigit(password)) {
            return false;
        }

        // Check if the password contains at least one special character
        if (!containsSpecialCharacter(password)) {
            return false;
        }

        // If all conditions are met, the password is strong
        return true;
    }

    private static boolean containsUppercase(String password) {
        return password.matches(".*[A-Z].*");
    }

    private static boolean containsLowercase(String password) {
        return password.matches(".*[a-z].*");
    }

    private static boolean containsDigit(String password) {
        return password.matches(".*\\d.*");
    }

    private static boolean containsSpecialCharacter(String password) {
        Pattern specialCharPattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher matcher = specialCharPattern.matcher(password);
        return matcher.find();
    }
}
