package com.example.springbootfinal.service;

import com.example.springbootfinal.domain.userEntity.BaseUser;

public interface BaseUserService {
    BaseUser findByFirstName(String name);
}
