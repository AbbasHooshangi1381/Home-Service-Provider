package com.example.springbootfinal.controller;

import com.example.springbootfinal.dto.Expert.ExpertSaveDto;
import com.example.springbootfinal.email.service.BeforeRegistration;
import com.example.springbootfinal.registeration.RegistrationRequest;
import com.example.springbootfinal.service.AdminService;
import com.example.springbootfinal.service.CustomerService;
import com.example.springbootfinal.service.ExpertService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/registration")
@SuppressWarnings("unused")
public class RegistrationController {

    @Autowired
    ExpertService expertService;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    AdminService adminService;
    @Autowired
    CustomerService customerService;
    @Autowired
    BeforeRegistration beforeRegistration;


    @PostMapping("/register-Expert")
    public ResponseEntity<String> saveExpert(@Valid @RequestBody ExpertSaveDto expertSaveDto) throws IOException {
         beforeRegistration.registerForExpert(expertSaveDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("ok!");
    }

    @PostMapping("/register-customer")
    public ResponseEntity<String> saveCustomer(@Valid @RequestBody RegistrationRequest request) {
        beforeRegistration.registerForCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("ok!");
    }

    @PostMapping("/register-admin")
    public ResponseEntity<String> saveAdmin(@Valid @RequestBody RegistrationRequest request) {
        beforeRegistration.registerForAdmin(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("ok!");
    }
    @GetMapping(path = "/confirm")
    public String confirm(@RequestParam String token) {
        return beforeRegistration.confirmToken(token);
    }
}
