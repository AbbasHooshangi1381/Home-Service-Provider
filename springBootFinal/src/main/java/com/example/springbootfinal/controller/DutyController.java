package com.example.springbootfinal.controller;

import com.example.springbootfinal.domain.serviceEntity.Duty;
import com.example.springbootfinal.domain.serviceEntity.SubDuty;
import com.example.springbootfinal.dto.Admin.BaseResponseDto;
import com.example.springbootfinal.repository.DutyRepository;
import com.example.springbootfinal.service.DutyService;
import com.example.springbootfinal.service.impl.DutyServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/duty")
public class DutyController {

    private DutyService dutyService;
    private DutyRepository dutyRepository;

    public DutyController(DutyService dutyService, DutyRepository dutyRepository) {
        this.dutyService = dutyService;
        this.dutyRepository = dutyRepository;
    }

    @PostMapping("/{saveDuty}")
    public ResponseEntity<Duty> saveExpert(@PathVariable String saveDuty){
         Duty duty = dutyService.saveServiceByAdmin(saveDuty);
         return ResponseEntity.status(HttpStatus.CREATED).body(duty);
    }
    @GetMapping("/showDuty/{dutyName}")
    public ResponseEntity<List<SubDuty>> showSubDuty(@PathVariable String dutyName){
         List<SubDuty> subDuties = dutyService.showSubDuty(dutyName);

         return ResponseEntity.ok(subDuties);
    }
}
