package com.example.springbootfinal.controller;

import com.example.springbootfinal.domain.serviceEntity.Duty;
import com.example.springbootfinal.dto.duty.DutyResponseDto;
import com.example.springbootfinal.repository.DutyRepository;
import com.example.springbootfinal.service.DutyService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("saveDuty/{saveDuty}")
    public ResponseEntity<DutyResponseDto> saveExpert(@PathVariable String saveDuty) {
        Duty duty = dutyService.saveServiceByAdmin(saveDuty);
        DutyResponseDto map = modelMapper.map(duty, DutyResponseDto.class);
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }
}
