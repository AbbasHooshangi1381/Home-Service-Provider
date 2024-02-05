package com.example.springbootfinal.service;

import com.example.springbootfinal.domain.serviceEntity.Duty;
import com.example.springbootfinal.domain.serviceEntity.SubDuty;

import java.util.List;

public interface DutyService {
    Duty saveServiceByAdmin(String dutyName);
  //  List<SubDuty> showSubDuty(String dutyName);

}
