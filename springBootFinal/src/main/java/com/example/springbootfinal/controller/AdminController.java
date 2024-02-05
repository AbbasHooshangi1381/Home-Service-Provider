package com.example.springbootfinal.controller;

import com.example.springbootfinal.domain.serviceEntity.SubDuty;
import com.example.springbootfinal.domain.userEntity.Admin;
import com.example.springbootfinal.domain.userEntity.Expert;
import com.example.springbootfinal.dto.Admin.*;
import com.example.springbootfinal.exception.NotFoundException;
import com.example.springbootfinal.repository.AdminRepository;
import com.example.springbootfinal.repository.ExpertRepository;
import com.example.springbootfinal.repository.SubDutyRepository;
import com.example.springbootfinal.service.AdminService;
import com.example.springbootfinal.service.SubDutyService;
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

    public AdminController(AdminService adminService, AdminRepository adminRepository,
                           ModelMapper modelMapper, SubDutyRepository subDutyRepository, ExpertRepository expertRepository) {
        this.adminService = adminService;
        this.adminRepository = adminRepository;
        this.modelMapper = modelMapper;
        this.subDutyRepository = subDutyRepository;
        this.expertRepository = expertRepository;
    }

    @PostMapping("/register-user")
    public ResponseEntity<BaseResponseDto> saveAdmin(@RequestBody BaseSaveDto adminSaveDto) {
        Admin savedAdmin = adminService.saveAdmin(adminSaveDto.getFirstName(), adminSaveDto.getLastName(),
                adminSaveDto.getEmail(), adminSaveDto.getUserName());

        BaseResponseDto adminResponseDto = modelMapper.map(savedAdmin, BaseResponseDto.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(adminResponseDto);
    }

    @GetMapping("/{username}/{password}")
    public ResponseEntity<BaseResponseDto> checkAdmin(@PathVariable String username, @PathVariable String password) {
        Admin admin = adminService.findByUserNameAndPassword(username, password).orElseThrow();
        if (admin != null) {
            BaseResponseDto baseResponseDto = modelMapper.map(admin, BaseResponseDto.class);
            return new ResponseEntity<>(baseResponseDto, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}/{newPassword}")
    public ResponseEntity<String> changePassword(@PathVariable Integer id,
                                                          @PathVariable String newPassword) {

      adminService.changePassword(id, newPassword);
        return ResponseEntity.ok("password changed ! ");
    }

/*    @PostMapping("/addSubDutyToExpert/{expertId}/{subDutyId}")
    public ResponseEntity<String> addingSubDutyToExpert(@PathVariable Integer expertId, @PathVariable Integer subDutyId) {

        adminService.addingSubDutyToExpert(expertId, subDutyId);

        return ResponseEntity.ok("SubDuty added to Expert successfully");
    }

    @DeleteMapping("/deletingSubDutyFromExpert/{expertId}/{subDutyId}")
    public ResponseEntity<String> deletingSubDutyFromExpert(@PathVariable Integer expertId, @PathVariable Integer subDutyId) {
        adminService.deletingSubDutyToExpert(expertId, subDutyId);
        return ResponseEntity.noContent().build();
    }*/

}
