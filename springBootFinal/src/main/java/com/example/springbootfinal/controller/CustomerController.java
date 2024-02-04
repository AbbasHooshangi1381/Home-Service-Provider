package com.example.springbootfinal.controller;


import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.userEntity.Admin;
import com.example.springbootfinal.domain.userEntity.Customer;
import com.example.springbootfinal.dto.Admin.BaseChangePasswordDto;
import com.example.springbootfinal.dto.Admin.BaseResponseDto;
import com.example.springbootfinal.dto.Admin.BaseSaveDto;
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
    private  ModelMapper modelMapper;


    @PostMapping("/register-user")
    public ResponseEntity<BaseResponseDto> saveCustomer(@RequestBody BaseSaveDto adminSaveDto) {
        Customer savedCustomer = customerService.saveCustomer(adminSaveDto.getFirstName(), adminSaveDto.getLastName(),
                adminSaveDto.getEmail(), adminSaveDto.getUserName());

        BaseResponseDto adminResponseDto = modelMapper.map(savedCustomer, BaseResponseDto.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(adminResponseDto);
    }

    @GetMapping("/{userName}/{password}")
    public ResponseEntity<BaseSaveDto> checkAdmin(@PathVariable String userName, @PathVariable String password) {
        Customer customer = customerService.findByFirstNameAndPassword(userName, password).orElse(null);
        if (customer != null) {
            BaseSaveDto adminSaveDto = modelMapper.map(customer, BaseSaveDto.class);
            return new ResponseEntity<>(adminSaveDto, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/admin/{id}/{Password}")
    public ResponseEntity<BaseResponseDto> changePassword(@PathVariable Integer id, @PathVariable String Password) {

        String password = customerService.changePassword(id, Password);

        final BaseResponseDto map = modelMapper.map(password, BaseResponseDto.class);

        return new ResponseEntity<>(map, HttpStatus.OK);

    }

    @PutMapping("/start/{orderId}")
    public ResponseEntity<String> startOrder(@PathVariable Integer orderId) {
        customerService.changeStatusOfOrderByCustomerStarted(orderId);
        return ResponseEntity.ok("Order " + orderId + " has been started.");
    }

    @PutMapping("/finish/{orderId}")
    public ResponseEntity<String> finishOrder(@PathVariable Integer orderId) {
        customerService.changeStatusOfOrderByCustomerToFinish(orderId);
        return ResponseEntity.ok("Order " + orderId + " has been marked as finished.");
    }


}
