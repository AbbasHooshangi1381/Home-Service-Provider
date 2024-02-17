package com.example.springbootfinal.service;
import com.example.springbootfinal.domain.userEntity.Admin;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.Optional;
@SuppressWarnings("unused")
public interface AdminService extends UserDetailsService {
    Admin changePassword(String oldPassword,String newPassword);
    Optional<Admin> findByUserNameAndPassword(String username, String password);

}
