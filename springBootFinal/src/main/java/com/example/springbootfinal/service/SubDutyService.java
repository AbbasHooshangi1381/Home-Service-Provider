package com.example.springbootfinal.service;

import com.example.springbootfinal.domain.serviceEntity.SubDuty;

import java.util.List;

public interface SubDutyService {
    SubDuty saveSubDutyByAdmin(Integer dutyId, String subServiceName, Double priceOfSubDuty, String description );
    void changeDescriptionOfSubDuty(Integer subDutyId, String newDescription);
    void changePriceOfSubDutyByAdmin(Integer subDutyId, Double newPrice);
    void registerExpertInOneSubDuty(Integer expertId, Integer subServiceId);
    void deleteExpertInSubDutyField(Integer subDutyId, Integer expertId);
    List<SubDuty> showSubDutyOfOneDuty(String dutyName);
}
