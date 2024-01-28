package com.example.springbootfinal.service;

import com.example.springbootfinal.domain.serviceEntity.SubDuty;

import java.sql.SQLException;

public interface SubDutyService {

    void saveSubDutyByAdmin(Integer dutyId, SubDuty subDuty);

    void changeDescriptionOfSubDuty(Integer subDutyId, String newDescription);

    void changePriceOfSubDutyByAdmin(Integer subDutyId, Double newPrice);

    void showSubDuty();

}
