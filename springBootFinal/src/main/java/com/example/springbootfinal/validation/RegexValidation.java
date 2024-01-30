package com.example.springbootfinal.validation;

import java.util.regex.Pattern;

public class RegexValidation {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static boolean validationName(String firstName){
        Pattern pattern =Pattern.compile("^[a-zA-Z]{1,20}$");
        return firstName.matches(pattern.pattern());
    }
    public static boolean validateEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

}
