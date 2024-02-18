package com.example.springbootfinal.service.impl;
import com.example.springbootfinal.domain.userEntity.BaseUser;
import com.example.springbootfinal.repository.BaseUserRepository;
import com.example.springbootfinal.service.BaseUserService;
import com.example.springbootfinal.speicification.Specifications;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BaseUserServiceImpl implements BaseUserService  {
    BaseUserRepository baseUserRepository;

    public BaseUserServiceImpl(BaseUserRepository baseUserRepository) {
        this.baseUserRepository = baseUserRepository;
    }

    @Override
    public List<BaseUser> generateReport(Map<String, Object> criteria) {
        Specification<BaseUser> spec = Specifications.reportCustomerExpert(criteria);
        return baseUserRepository.findAll(spec);
    }
}
