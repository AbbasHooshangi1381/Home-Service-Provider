package com.example.springbootfinal.service;

import com.example.springbootfinal.domain.other.CustomerOrder;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface ExpertService {

     void changeStatusOfExpertByAdmin(Integer expertId);

     void saveExpert(String firstName, String lastName, String email, String userName, LocalDate timeOfSignIn,String filePath) throws IOException ;

     Boolean changePassword(Integer id , String newPassword);

     byte[] saveImageByIdToSystem(Integer id);

     void registerExpertInOneService(Integer expertId , Integer subServiceId);

     void sendOfferForSubDuty(Integer expertId , Integer customerOrderId) throws SQLException;

     List<CustomerOrder> customerOrderList();



}
