package com.example.springbootfinal.service;

import com.example.springbootfinal.domain.userEntity.Customer;

import java.time.LocalDate;

public interface CustomerService {
    Customer saveCustomer(String firstName, String lastName, String email, String userName, LocalDate timeOfSignIn);
    Boolean changePassword(Integer id , String newPassword);
    void changeStatusOfOrderByCustomerStarted(Integer orderId);
    void changeStatusOfOrderByCustomerToFinish(Integer orderId);
}
