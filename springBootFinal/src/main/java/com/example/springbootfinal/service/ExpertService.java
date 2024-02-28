package com.example.springbootfinal.service;

import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.other.ExpertRating;
import com.example.springbootfinal.domain.userEntity.Expert;

import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;

public interface ExpertService extends UserDetailsService {
     Expert changeStatusOfExpertByAdmin(Integer expertId);
    Integer showStarOfExpert(String userName);
    List<Expert> findExpertsBySubDuty(String subDutyName);
    ExpertRating findExpertRatings();
    List<CustomerOrder> showOrderToExpert(String username);


}
