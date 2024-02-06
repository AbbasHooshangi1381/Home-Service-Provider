package com.example.springbootfinal.controller;


import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.userEntity.Admin;
import com.example.springbootfinal.domain.userEntity.Customer;
import com.example.springbootfinal.dto.Admin.BaseChangePasswordDto;
import com.example.springbootfinal.dto.Admin.BaseResponseDto;
import com.example.springbootfinal.dto.Admin.BaseSaveDto;
import com.example.springbootfinal.dto.customer.UserPassDto;
import com.example.springbootfinal.exception.NotFoundException;
import com.example.springbootfinal.repository.AdminRepository;
import com.example.springbootfinal.repository.CustomerRepository;
import com.example.springbootfinal.repository.ExpertRepository;
import com.example.springbootfinal.repository.SubDutyRepository;
import com.example.springbootfinal.service.AdminService;
import com.example.springbootfinal.service.CustomerOrderService;
import com.example.springbootfinal.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ModelMapper modelMapper;


    @PostMapping("/register-user")
    public ResponseEntity<BaseResponseDto> saveCustomer(@RequestBody BaseSaveDto adminSaveDto) {
        Customer savedCustomer = customerService.saveCustomer(adminSaveDto.getFirstName(), adminSaveDto.getLastName(),
                adminSaveDto.getEmail(), adminSaveDto.getUserName());

        BaseResponseDto adminResponseDto = modelMapper.map(savedCustomer, BaseResponseDto.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(adminResponseDto);
    }

    @GetMapping("/login/{username}/{password}")
    public ResponseEntity<BaseResponseDto> checkCustomer(@PathVariable String username, @PathVariable String password) {
        Customer customer = customerService.findByUserNameAndPassword(username, password).orElseThrow();
        if (customer != null) {
            BaseResponseDto baseResponseDto = modelMapper.map(customer, BaseResponseDto.class);
            return new ResponseEntity<>(baseResponseDto, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/changePassword/{id}/{password}")
    public ResponseEntity<String> changePassword(@PathVariable Integer id, @PathVariable String password) {

         customerService.changePassword(id, password);

        return ResponseEntity.ok("pasword changed !");
    }


}
