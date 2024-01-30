package com.example.springbootfinal.service;

import com.example.springbootfinal.domain.serviceEntity.SubDuty;
import com.example.springbootfinal.domain.userEntity.Expert;

import java.util.List;

public interface SubDutyService {
    void saveSubDutyByAdmin(Integer dutyId,String subServiceName,Double priceOfSubDuty,String description );
    void changeDescriptionOfSubDuty(Integer subDutyId, String newDescription);
    void changePriceOfSubDutyByAdmin(Integer subDutyId, Double newPrice);
    List<SubDuty> showSubDuty();
    void registerExpertInOneSubDuty(Integer expertId, Integer subServiceId);
    void deleteExpertInSubDutyField(Integer subDutyId);
}
