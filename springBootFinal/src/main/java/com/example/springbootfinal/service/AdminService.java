package com.example.springbootfinal.service;

import com.example.springbootfinal.domain.userEntity.Admin;

import java.time.LocalDate;
import java.util.Optional;

public interface AdminService {
    Boolean changePassword(Integer id, String newPassword);
    Admin saveAdmin(String firstName, String lastName, String email, String userName, LocalDate timeOfSignIn);
    Optional<Admin> findByUserNameAndPassword(String username, String password);
}
