package com.example.springbootfinal.service;

import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.userEntity.Expert;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ExpertService extends UserDetailsService {
     Expert changeStatusOfExpertByAdmin(Integer expertId);
     Optional<Expert> findByUserNameAndPassword(String username, String password);
     Expert changePassword(String oldPassword,String newPassword);
     byte[] saveImageByIdToSystem(Integer id) throws IOException;
     void changeStatusOfOrderByExpertStarted(Integer orderId);
     void changeStatusOfOrderByCustomerToFinish(Integer orderId,String timeOfFinishingWork);
     List<Expert> findAllExpertsByCriteria(Map<String, String> criteria);
     List<Expert> findExpertByStar(Map<String, String> params);
     List<CustomerOrder> customerOrderListOfExpert(Integer expertId, String statusOfOrder);

}
