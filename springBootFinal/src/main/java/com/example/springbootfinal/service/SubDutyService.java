package com.example.springbootfinal.service;

import java.sql.SQLException;

public interface SubDutyService {

    void saveOrder(Integer customerId, Integer subServiceId) throws SQLException;

    void saveSubDutyByAdmin(Integer dutyId, String subServiceName);

    void changeDescriptionOfSubDuty(Integer subDutyId, String newDescription);

    void changePriceOfSubDutyByAdmin(Integer subDutyId, Double newPrice);

    void showSubDuty();

}
