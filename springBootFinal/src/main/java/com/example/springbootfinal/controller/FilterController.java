package com.example.springbootfinal.controller;

import com.example.springbootfinal.domain.userEntity.Customer;
import com.example.springbootfinal.domain.userEntity.Expert;
import com.example.springbootfinal.dto.Expert.RequestDto;
import com.example.springbootfinal.repository.ExpertRepository;
import com.example.springbootfinal.service.FilterSpecificationFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/filterController")
public class FilterController {

    @Autowired
    ExpertRepository expertRepository;
    @Autowired
    FilterSpecificationFilterService<Expert> filterSpecificationFilterService;

    @Autowired
    FilterSpecificationFilterService<Customer> customerFilterSpecificationFilterService;

    @GetMapping("/specification")
    public List<Expert> getExpert(@RequestBody RequestDto requestDto){
         Specification<Expert> searchSpecification = filterSpecificationFilterService.getSearchSpecification(requestDto.getSearchRequestDto(), requestDto.getGlobalOperator());
        return expertRepository.findAll(searchSpecification);
    }
}
