package com.example.springbootfinal.controller;

import com.example.springbootfinal.domain.userEntity.BaseUser;
import com.example.springbootfinal.dto.Admin.BaseUserDto;
import com.example.springbootfinal.repository.BaseUserRepository;
import com.example.springbootfinal.service.impl.BaseUserSpecification;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.jpa.domain.Specification;
import java.util.List;

@RestController
@RequestMapping("/baseUsers")
public class BaseUserController {
    private final BaseUserRepository baseUserRepository;

    public BaseUserController(BaseUserRepository baseUserRepository) {
        this.baseUserRepository = baseUserRepository;
    }

    @PostMapping("/searchUser")
    public List<BaseUser> searchUser(@RequestBody BaseUserDto baseUserDto) {
        Specification<BaseUser> spec = Specification.where(BaseUserSpecification.isExpertOrCustomer())
                .and(BaseUserSpecification.hasFirstName(baseUserDto.getFirstName()))
                .and(BaseUserSpecification.hasLastName(baseUserDto.getLastName()))
                .and(BaseUserSpecification.hasSubDuty(baseUserDto.getSubDuty()))
                .and(BaseUserSpecification.minRating(baseUserDto.getMinRating()))
                .and(BaseUserSpecification.maxRating(baseUserDto.getMaxRating()));

        return baseUserRepository.findAll(spec);
    }
}
