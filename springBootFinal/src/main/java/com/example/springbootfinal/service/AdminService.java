package com.example.springbootfinal.service;
import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.userEntity.Admin;
import com.example.springbootfinal.domain.userEntity.BaseUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;
@SuppressWarnings("unused")
public interface AdminService extends UserDetailsService {
    BaseUser changePassword(String userName, String newPassword);

}
