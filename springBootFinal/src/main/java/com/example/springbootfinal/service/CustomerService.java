package com.example.springbootfinal.service;

import com.example.springbootfinal.domain.userEntity.Customer;

import java.util.List;

public interface CustomerService {
    void SaveCustomer();

    Boolean changePassword(Integer id , String newPassword);

}
