package com.example.springbootfinal.controller;

import com.example.springbootfinal.domain.serviceEntity.Duty;
import com.example.springbootfinal.domain.serviceEntity.SubDuty;
import com.example.springbootfinal.dto.Admin.BaseResponseDto;
import com.example.springbootfinal.dto.duty.DutySaveDto;
import com.example.springbootfinal.exception.NotFoundException;
import com.example.springbootfinal.repository.DutyRepository;
import com.example.springbootfinal.service.DutyService;
import com.example.springbootfinal.service.impl.DutyServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/duty")
public class DutyController {

    private DutyService dutyService;
    private DutyRepository dutyRepository;
    private ModelMapper modelMapper;

    public DutyController(DutyService dutyService, DutyRepository dutyRepository, ModelMapper modelMapper) {
        this.dutyService = dutyService;
        this.dutyRepository = dutyRepository;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/{saveDuty}")
    public ResponseEntity<DutySaveDto> saveExpert(@PathVariable String saveDuty) {
        Duty duty = dutyService.saveServiceByAdmin(saveDuty);
        DutySaveDto map = modelMapper.map(duty, DutySaveDto.class);
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }
}
