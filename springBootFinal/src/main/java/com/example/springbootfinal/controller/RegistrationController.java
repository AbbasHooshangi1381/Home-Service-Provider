package com.example.springbootfinal.controller;

import com.example.springbootfinal.dto.Expert.ExpertSaveDto;
import com.example.springbootfinal.email.service.BeforeRegistration;
import com.example.springbootfinal.dto.registeration.RegistrationRequest;
import com.example.springbootfinal.service.AdminService;
import com.example.springbootfinal.service.CustomerService;
import com.example.springbootfinal.service.ExpertService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/registration")
@SuppressWarnings("unused")
public class RegistrationController {

    ExpertService expertService;
    ModelMapper modelMapper;
    AdminService adminService;
    CustomerService customerService;
    BeforeRegistration beforeRegistration;

    public RegistrationController(ExpertService expertService, ModelMapper modelMapper, AdminService adminService,
                                  CustomerService customerService, BeforeRegistration beforeRegistration) {
        this.expertService = expertService;
        this.modelMapper = modelMapper;
        this.adminService = adminService;
        this.customerService = customerService;
        this.beforeRegistration = beforeRegistration;
    }

    @PostMapping(value = "/register-Expert")
    public ResponseEntity<String> saveExpert(@RequestBody ExpertSaveDto expertSaveDto) throws IOException {
        beforeRegistration.registerForExpert(expertSaveDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("ok!");
    }

    @PostMapping("/register-customer")
    public ResponseEntity<String> saveCustomer(@RequestBody RegistrationRequest request) {
        beforeRegistration.registerForCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("ok!");
    }

    @PostMapping("/register-admin")
    public ResponseEntity<String> saveAdmin(@RequestBody RegistrationRequest request) {
        beforeRegistration.registerForAdmin(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("ok!");
    }

    @GetMapping(path = "/confirm")
    public String confirm(@RequestParam String token) {
        return beforeRegistration.confirmToken(token);
    }
}
