package com.example.springbootfinal.service;

import com.example.springbootfinal.domain.userEntity.Customer;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CustomerService {
    Customer saveCustomer(String firstName, String lastName, String email, String userName);
    String changePassword(Integer id , String password);
    Optional<Customer> findByUserNameAndPassword(String username, String password);
    List<Customer> findAllCustomerByCriteria(Map<String, String> criteria);
}
