package com.example.springbootfinal.service;

import com.example.springbootfinal.domain.userEntity.BaseUser;

import java.util.List;
import java.util.Map;

public interface BaseUserService {
    List<BaseUser> generateReport(Map<String, Object> criteria);
}
