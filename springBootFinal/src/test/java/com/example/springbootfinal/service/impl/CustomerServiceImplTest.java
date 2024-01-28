package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.userEntity.Admin;
import com.example.springbootfinal.domain.userEntity.Customer;
import com.example.springbootfinal.repository.CustomerRepository;
import com.example.springbootfinal.service.CustomerService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerServiceImplTest {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerService customerService;

    Customer customer;

    @BeforeEach
    void setUp() {
        String validFirstName = "John";
        String validLastName = "Smith";
        String validEmail = "abbas.ali@example.com";
        String validUserName = "johnsmith";
        LocalDate validTimeOfSignIn = LocalDate.now();
        customer = customerService.saveCustomer(validFirstName, validLastName, validEmail, validUserName, validTimeOfSignIn);
    }

    @Test
    @Order(1)
    void saveCustomer() {
        assertNotNull(customer);
    }

    @Test
    @Order(1)
    void changePassword() {
         String email = customer.getEmail();
        Optional<Customer> byEmail = customerRepository.findByEmail(email);
        Integer id = byEmail.get().getId();
        assertNotNull(id);
        String newPassword = "newPassword123";

        customerService.changePassword(id, newPassword);

        String changedPassword = byEmail.get().getPassword();
        assertNotNull(changedPassword);
        assertEquals(newPassword, changedPassword);
    }
}