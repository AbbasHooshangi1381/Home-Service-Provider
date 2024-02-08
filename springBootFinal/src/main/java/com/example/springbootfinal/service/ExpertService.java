package com.example.springbootfinal.service;

import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.userEntity.Expert;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ExpertService {
     Expert changeStatusOfExpertByAdmin(Integer expertId);
     Expert saveExpert(String firstName, String lastName, String email, String userName, String filePath) throws IOException ;
     Optional<Expert> findByUserNameAndPassword(String username, String password);
     Expert changePassword(Integer id , String newPassword);
     byte[] saveImageByIdToSystem(Integer id) throws IOException;
     void changeStatusOfOrderByExpertStarted(Integer orderId);
     void changeStatusOfOrderByCustomerToFinish(Integer orderId,String timeOfFinishingWork);
     List<Expert> findByCustomerOrderIdOrderByExpertStarsDesc(Integer customerOrderId);
}
