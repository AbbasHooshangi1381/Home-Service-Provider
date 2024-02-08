package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.userEntity.BaseUser;
import com.example.springbootfinal.repository.BaseUserRepository;
import com.example.springbootfinal.service.BaseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseUserServiceImpl implements BaseUserService {
    @Autowired
    BaseUserRepository baseUserRepository;

    @Override
    public BaseUser findByFirstName(String name) {
        return baseUserRepository.findByFirstName(name);
    }
}
