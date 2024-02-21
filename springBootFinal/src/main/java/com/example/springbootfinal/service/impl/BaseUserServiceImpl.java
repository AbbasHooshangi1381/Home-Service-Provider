package com.example.springbootfinal.service.impl;
import com.example.springbootfinal.domain.userEntity.BaseUser;
import com.example.springbootfinal.exception.NotFoundException;
import com.example.springbootfinal.repository.BaseUserRepository;
import com.example.springbootfinal.service.BaseUserService;
import com.example.springbootfinal.speicification.Specifications;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BaseUserServiceImpl implements BaseUserService  {
    BaseUserRepository baseUserRepository;
    BCryptPasswordEncoder passwordEncoder;

    public BaseUserServiceImpl(BaseUserRepository baseUserRepository, BCryptPasswordEncoder passwordEncoder) {
        this.baseUserRepository = baseUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<BaseUser> generateReport(Map<String, Object> criteria) {
        Specification<BaseUser> spec = Specifications.reportCustomerExpert(criteria);
        return baseUserRepository.findAll(spec);
    }
    @Override
    public BaseUser changePassword(String userName, String newPassword) {
        BaseUser baseUser = baseUserRepository.findByUserName(userName).orElseThrow(() ->
                new NotFoundException("i can not found this userName"));
        String encode = passwordEncoder.encode(newPassword);
        baseUser.setPassword(encode);
        baseUserRepository.save(baseUser);
        return baseUser;
    }
}
