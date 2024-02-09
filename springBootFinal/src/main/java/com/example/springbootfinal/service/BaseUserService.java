package com.example.springbootfinal.service;

import com.example.springbootfinal.domain.userEntity.BaseUser;
import com.example.springbootfinal.domain.userEntity.Customer;
import com.example.springbootfinal.domain.userEntity.Expert;

import java.util.List;
import java.util.Map;

public interface BaseUserService {
    List<Expert> findAllExpertByCriteria(Map<String, String> param);
    List<Customer> findAllCustomersByCriteria(Map<String, String> param);
}
