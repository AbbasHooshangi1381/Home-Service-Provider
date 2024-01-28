package com.example.springbootfinal.service;

import com.example.springbootfinal.domain.serviceEntity.Duty;

public interface DutyService {
    Duty saveServiceByAdmin(String dutyName);
}
