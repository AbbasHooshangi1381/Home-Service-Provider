package com.example.springbootfinal.controller;

import com.example.springbootfinal.domain.serviceEntity.Duty;
import com.example.springbootfinal.domain.serviceEntity.SubDuty;
import com.example.springbootfinal.domain.userEntity.Admin;
import com.example.springbootfinal.domain.userEntity.Expert;
import com.example.springbootfinal.dto.Admin.*;
import com.example.springbootfinal.dto.subDity.SubDutyResponseDto;
import com.example.springbootfinal.dto.subDity.SubDutySaveRequestDto;
import com.example.springbootfinal.exception.NotFoundException;
import com.example.springbootfinal.repository.AdminRepository;
import com.example.springbootfinal.repository.ExpertRepository;
import com.example.springbootfinal.repository.SubDutyRepository;
import com.example.springbootfinal.service.AdminService;
import com.example.springbootfinal.service.DutyService;
import com.example.springbootfinal.service.SubDutyService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private AdminService adminService;
    private AdminRepository adminRepository;
    private final ModelMapper modelMapper;
    private SubDutyRepository subDutyRepository;
    private ExpertRepository expertRepository;
    private DutyService dutyService;
    private SubDutyService subDutyService;

    public AdminController(AdminService adminService, AdminRepository adminRepository,
                           ModelMapper modelMapper, SubDutyRepository subDutyRepository, ExpertRepository expertRepository
    ,DutyService dutyService,SubDutyService subDutyService) {
        this.adminService = adminService;
        this.adminRepository = adminRepository;
        this.modelMapper = modelMapper;
        this.subDutyRepository = subDutyRepository;
        this.expertRepository = expertRepository;
        this.dutyService=dutyService;
        this.subDutyService=subDutyService;
    }

    @PostMapping("/register-user")
    public ResponseEntity<BaseResponseDto> saveAdmin(@Valid @RequestBody BaseSaveDto adminSaveDto) {
        Admin savedAdmin = adminService.saveAdmin(adminSaveDto.getFirstName(), adminSaveDto.getLastName(),
                adminSaveDto.getEmail(), adminSaveDto.getUserName());
        BaseResponseDto adminResponseDto = modelMapper.map(savedAdmin, BaseResponseDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(adminResponseDto);
    }
    @GetMapping("/login/{username}/{password}")
    public ResponseEntity<BaseResponseDto> checkAdmin( @PathVariable String username, @PathVariable String password) {
        Admin admin = adminService.findByUserNameAndPassword(username, password).get();
            BaseResponseDto baseResponseDto = modelMapper.map(admin, BaseResponseDto.class);
            return new ResponseEntity<>(baseResponseDto, HttpStatus.OK);
    }
    @PutMapping("/changePassword/{oldPassword}/{newPassword}")
    public ResponseEntity<String> changePassword( @PathVariable String oldPassword, @PathVariable String newPassword) {
      adminService.changePassword(oldPassword, newPassword);
        return ResponseEntity.ok("password changed ! ");
    }

    //////////////////////////////////////////////////////////////////
    @PostMapping("saveDuty/{saveDuty}")
    public ResponseEntity<String> saveExpert(@PathVariable String saveDuty) {
        Duty duty = dutyService.saveServiceByAdmin(saveDuty);

        if (duty == null) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>("saved!", HttpStatus.CREATED);
    }

    @PostMapping("/saveSubDuty")
    public ResponseEntity<SubDutyResponseDto> saveSubDuty(@Valid @RequestBody SubDutySaveRequestDto subDutySaveDto) {
        Integer dutyId = subDutySaveDto.getDutyId();
        Double priceOfSubDuty = subDutySaveDto.getPriceOfSubDuty();
        String description = subDutySaveDto.getDescription();
        String subServiceName = subDutySaveDto.getSubServiceName();
        SubDuty subDuty = subDutyService.saveSubDutyByAdmin(dutyId, subServiceName, priceOfSubDuty, description);
        if (subDuty==null){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        SubDutyResponseDto map = modelMapper.map(subDuty, SubDutyResponseDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(map);
    }
    @PutMapping("/changeDescriptionOfSubDuty/{subDutyId}/{newDescription}")
    public ResponseEntity<String> changeDescriptionOfSubDuty( @PathVariable Integer subDutyId, @PathVariable String newDescription) {
        subDutyService.changeDescriptionOfSubDuty(subDutyId, newDescription);
        return ResponseEntity.ok("Description of SubDuty with ID " + subDutyId + " has been changed.");
    }
    @PutMapping("/changePriceOfSubDuty/{subDutyId}/{newPrice}")
    public ResponseEntity<String> changePriceOfSubDuty( @PathVariable Integer subDutyId, @PathVariable Double newPrice){
        subDutyService.changePriceOfSubDutyByAdmin(subDutyId,newPrice);
        return ResponseEntity.ok("price of subDuty with ID"+subDutyId+"has been changed");
    }
    @PutMapping("/registerExpertInOneSubDuty/{expertId}/{subServiceId}")
    public ResponseEntity<String> registerExpertInOneSubDuty( @PathVariable Integer expertId, @PathVariable Integer subServiceId) {
        subDutyService.registerExpertInOneSubDuty(expertId, subServiceId);
        return ResponseEntity.ok("Expert with ID " + expertId + " added to SubService with ID " + subServiceId);
    }
    @PutMapping("/deleteExpertInSubDutyField/{subServiceId}/{expertId}")
    public ResponseEntity<String> deleteExpertInSubDutyField( @PathVariable Integer subServiceId,@PathVariable Integer expertId){
        subDutyService.deleteExpertInSubDutyField(subServiceId,expertId);
        return ResponseEntity.ok(" subDuty with ID"+subServiceId+"has deleted ");
    }

}
