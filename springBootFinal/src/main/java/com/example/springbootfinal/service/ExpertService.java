package com.example.springbootfinal.service;

import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.other.ExpertRating;
import com.example.springbootfinal.domain.userEntity.Expert;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
public interface ExpertService extends UserDetailsService {
     Expert changeStatusOfExpertByAdmin(Integer expertId);
    List<Expert> findExpertsBySubDuty(String subDutyName);
    ExpertRating findExpertRatings();
    List<Expert> findAllExpertByCriteria(Map<String, String> params);


}
