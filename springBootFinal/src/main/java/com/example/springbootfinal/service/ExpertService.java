package com.example.springbootfinal.service;

import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.userEntity.Expert;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExpertService {
     void changeStatusOfExpertByAdmin(Integer expertId);
     Expert saveExpert(String firstName, String lastName, String email, String userName, String filePath) throws IOException ;
     Optional<Expert> findByUserNameAndPassword(String username, String password);
     Boolean changePassword(Integer id , String newPassword);
     byte[] saveImageByIdToSystem(Integer id) throws IOException;
     void sendOfferForSubDuty(Integer expertId, Integer customerOrderId,double suggestionPrice,String timeOfWork) throws SQLException;
     List<CustomerOrder> customerOrderList();
}
