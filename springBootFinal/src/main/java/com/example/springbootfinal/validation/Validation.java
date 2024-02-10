package com.example.springbootfinal.validation;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class Validation {
    public static String generateRandomPassword() {
        String uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String specialCharacters = "!@#$%^&";
        String lowercaseLettersAndNumbers = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        SecureRandom random = new SecureRandom();
        password.append(uppercaseLetters.charAt(random.nextInt(uppercaseLetters.length())));
        password.append(specialCharacters.charAt(random.nextInt(specialCharacters.length())));
        for (int i = 2; i < 8; i++) {
            int randomIndex = random.nextInt(lowercaseLettersAndNumbers.length());
            char randomChar = lowercaseLettersAndNumbers.charAt(randomIndex);
            password.append(randomChar);
        }
        String shuffledPassword = shuffleString(password.toString());
        return shuffledPassword;
    }
    private static String shuffleString(String input) {
        List<Character> characters = input.chars().mapToObj(e -> (char)e).collect(Collectors.toList());
        Collections.shuffle(characters);
        return characters.stream().map(String::valueOf).collect(Collectors.joining());
    }
}

