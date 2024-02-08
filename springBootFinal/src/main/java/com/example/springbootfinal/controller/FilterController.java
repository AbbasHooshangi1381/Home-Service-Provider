package com.example.springbootfinal.controller;

import com.example.springbootfinal.domain.userEntity.BaseUser;
import com.example.springbootfinal.domain.userEntity.Customer;
import com.example.springbootfinal.domain.userEntity.Expert;
import com.example.springbootfinal.dto.Expert.RequestDto;
import com.example.springbootfinal.repository.BaseUserRepository;
import com.example.springbootfinal.repository.ExpertRepository;
import com.example.springbootfinal.service.FilterSpecificationFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filterController")
public class FilterController {

    @Autowired
    BaseUserRepository baseUserRepository;
    @Autowired
    FilterSpecificationFilterService<BaseUser> filterSpecificationFilterService;


    @GetMapping("/specification")
    public List<BaseUser> getExpert(@RequestBody RequestDto requestDto) {
        Specification<BaseUser> searchSpecification = filterSpecificationFilterService.getSearchSpecification(requestDto.getSearchRequestDto());
        return baseUserRepository.findAll(searchSpecification);
    }

}
