package com.example.springbootfinal.service;

import com.example.springbootfinal.domain.userEntity.Customer;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CustomerService extends UserDetailsService {
    String changePassword(String oldPassword,String newPassword);
    List<Customer> findAllCustomersByCriteria(Map<String, String> param);
}
