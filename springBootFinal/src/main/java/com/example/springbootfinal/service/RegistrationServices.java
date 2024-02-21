package com.example.springbootfinal.service;

import java.io.File;
import java.io.IOException;

public interface RegistrationServices {
    String saveAdmin(String firstName, String lastName, String email, String userName,String password);
    String saveCustomer(String firstName, String lastName, String email, String userName, String password);
    String saveExpert(String firstName, String lastName, String email, String userName, String filePath, String password) throws IOException;
    int enableAppUser(String email);
}
