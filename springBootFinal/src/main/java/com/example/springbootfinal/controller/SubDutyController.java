package com.example.springbootfinal.controller;

import com.example.springbootfinal.domain.serviceEntity.SubDuty;
import com.example.springbootfinal.dto.Admin.BaseResponseDto;
import com.example.springbootfinal.dto.subDity.SubDutyResponseDto;
import com.example.springbootfinal.dto.subDity.SubDutySaveDto;
import com.example.springbootfinal.exception.NotFoundException;
import com.example.springbootfinal.repository.SubDutyRepository;
import com.example.springbootfinal.service.SubDutyService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/subDuty")
public class SubDutyController {
    private SubDutyRepository subDutyRepository;
    private SubDutyService subDutyService;
    private ModelMapper modelMapper;

    public SubDutyController(SubDutyRepository subDutyRepository, SubDutyService subDutyService, ModelMapper modelMapper) {
        this.subDutyRepository = subDutyRepository;
        this.subDutyService = subDutyService;
        this.modelMapper=modelMapper;
    }
    @PostMapping("/saveSubDuty")
    public ResponseEntity<SubDutyResponseDto> saveSubDuty(@RequestBody SubDutySaveDto subDutySaveDto) {
        Integer dutyId = subDutySaveDto.getDutyId();
        Double priceOfSubDuty = subDutySaveDto.getPriceOfSubDuty();
        String description = subDutySaveDto.getDescription();
        String subServiceName = subDutySaveDto.getSubServiceName();
         SubDuty subDuty = subDutyService.saveSubDutyByAdmin(dutyId, subServiceName, priceOfSubDuty, description);

         SubDutyResponseDto map = modelMapper.map(subDuty, SubDutyResponseDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(map);
    }
    @PutMapping("/changeDescriptionOfSubDuty/{subDutyId}/{newDescription}")
    public ResponseEntity<String> changeDescriptionOfSubDuty(@PathVariable Integer subDutyId, @PathVariable String newDescription) {
        subDutyService.changeDescriptionOfSubDuty(subDutyId, newDescription);
        return ResponseEntity.ok("Description of SubDuty with ID " + subDutyId + " has been changed.");
    }
    @PutMapping("/changePriceOfSubDuty/{subDutyId}/{newPrice}")
    public ResponseEntity<String> changePriceOfSubDuty(@PathVariable Integer subDutyId, @PathVariable Double newPrice){
        subDutyService.changePriceOfSubDutyByAdmin(subDutyId,newPrice);
        return ResponseEntity.ok("price of subDuty with ID"+subDutyId+"has been changed");
    }
    @PutMapping("/registerExpertInOneSubDuty/{expertId}/{subServiceId}")
    public ResponseEntity<String> registerExpertInOneSubDuty(@PathVariable Integer expertId, @PathVariable Integer subServiceId) {
        subDutyService.registerExpertInOneSubDuty(expertId, subServiceId);
        return ResponseEntity.ok("Expert with ID " + expertId + " added to SubService with ID " + subServiceId);
    }
    @PutMapping("/deleteExpertInSubDutyField/{subServiceId}")
    public ResponseEntity<String> deleteExpertInSubDutyField(@PathVariable Integer subServiceId){
        subDutyService.deleteExpertInSubDutyField(subServiceId);
        return ResponseEntity.ok(" subDuty with ID"+subServiceId+"has deleted ");
    }
}
