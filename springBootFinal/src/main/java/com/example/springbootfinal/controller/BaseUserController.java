package com.example.springbootfinal.controller;

import com.example.springbootfinal.domain.userEntity.BaseUser;
import com.example.springbootfinal.domain.userEntity.Customer;
import com.example.springbootfinal.domain.userEntity.Expert;
import com.example.springbootfinal.dto.Admin.BaseUserDto;
import com.example.springbootfinal.dto.Expert.CriteriaSearchDto;
import com.example.springbootfinal.repository.BaseUserRepository;
import com.example.springbootfinal.service.CustomerService;
import com.example.springbootfinal.service.ExpertService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/baseUsers")
public class BaseUserController {


    private CustomerService customerService;
    private ExpertService expertService;
    private ModelMapper modelMapper;

    public BaseUserController(CustomerService customerService, ExpertService expertService, ModelMapper modelMapper) {
        this.customerService = customerService;
        this.expertService = expertService;
        this.modelMapper = modelMapper;
    }


    @PostMapping("/findAllCustomertByCriteria")
    //firstname - lastname - email - specialistField(select a serviceName) - averageScoresOrderBy(asc or desc)
    public List<CriteriaSearchDto> findAllCustomerByCriteria(@RequestBody Map<String, String> param) {

        List<CriteriaSearchDto> criteriaSearchDtoList = new ArrayList<>();
        List<Customer> allSpecialistsByCriteria = customerService.findAllCustomerByCriteria(param);
        for (Customer s : allSpecialistsByCriteria) {
            CriteriaSearchDto criteriaSearchDto = modelMapper.map(s, CriteriaSearchDto.class);
            criteriaSearchDtoList.add(criteriaSearchDto);
        }
        return criteriaSearchDtoList;
    }

    @PostMapping("/findAllExpertByCriteria")
    //firstname - lastname - email - specialistField(select a serviceName) - averageScoresOrderBy(asc or desc)
    public List<CriteriaSearchDto> findAllExpertByCriteria(@RequestBody Map<String, String> param) {

        List<CriteriaSearchDto> criteriaSearchDtoList = new ArrayList<>();
        List<Expert> allSpecialistsByCriteria = expertService.findAllExpertsByCriteria(param);
        for (Expert s : allSpecialistsByCriteria) {
            CriteriaSearchDto criteriaSearchDto = modelMapper.map(s, CriteriaSearchDto.class);
            criteriaSearchDtoList.add(criteriaSearchDto);
        }
        return criteriaSearchDtoList;
    }

}
