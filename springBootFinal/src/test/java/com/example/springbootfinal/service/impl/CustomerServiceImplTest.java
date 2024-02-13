package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.enumurations.StatusOfOrder;
import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.serviceEntity.Duty;
import com.example.springbootfinal.domain.serviceEntity.SubDuty;
import com.example.springbootfinal.domain.userEntity.Admin;
import com.example.springbootfinal.domain.userEntity.Customer;
import com.example.springbootfinal.repository.CustomerRepository;
import com.example.springbootfinal.repository.DutyRepository;
import com.example.springbootfinal.repository.SubDutyRepository;
import com.example.springbootfinal.service.CustomerOrderService;
import com.example.springbootfinal.service.CustomerService;
import jakarta.transaction.Transactional;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerServiceImplTest {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerService customerService;
    @Autowired
    CustomerOrderService customerOrderService;
    @Autowired
    DutyRepository dutyRepository;
    @Autowired
    SubDutyRepository subDutyRepository;

    Customer customer;
    Duty dutys;
    SubDuty subDutys;
    CustomerOrder customerOrder;

    @BeforeAll
    void setUp() throws SQLException {
        String validFirstName = "Jddddohn";
        String validLastName = "Smddddith";
        String validEmail = "abbadddds.ali@example.com";
        String validUserName = "johndddddsmith";
        customer = customerService.saveCustomer(validFirstName, validLastName, validEmail, validUserName);

        Duty duty = new Duty("electronic");
        dutys = dutyRepository.save(duty);

        SubDuty subDuty = new SubDuty("gaz", 500.00, "dafergergergrgrver", dutys);
        subDutys = subDutyRepository.save(subDuty);

        Integer id = customer.getId();
        assertNotNull(id);
        Integer id1 = subDutys.getId();
        assertNotNull(id1);
        String descriptionOfOrder = "you should it ! ";
        double proposedPrice = 8000.00;
        String timeOfWork = "1402/11/30";
        String address = "mashhad";
        StatusOfOrder waitingForSuggestExpert = StatusOfOrder.WAITING_FOR_SELECT_EXPERT;

        customerOrder = customerOrderService.saveOrder(descriptionOfOrder, proposedPrice, timeOfWork, address, waitingForSuggestExpert, id, id1);
    }

    @Test
    @Order(1)
    void saveCustomer() {
        String validFirstName = "John";
        String validLastName = "Smith";
        String validEmail = "abbas.ali@example.com";
        String validUserName = "johnsmith";
        Customer customer3 = customerService.saveCustomer(validFirstName, validLastName, validEmail, validUserName);
        assertNotNull(customer3);
    }

/*    @Test
    @Transactional
    @Order(2)
    void changePassword() {
        String email = customer.getEmail();
        Optional<Customer> byEmail = customerRepository.findByEmail(email);
        Integer id = byEmail.get().getId();
        assertNotNull(id);
        String newPassword = "newPassword123";

         String password = customerService.changePassword(id, newPassword);
        assertNotNull(password);

        String changedPassword = byEmail.get().getPassword();
        assertNotNull(changedPassword);
        assertEquals(newPassword, changedPassword);

         String password1 = customerService.changePassword(2, "aff52");
        assertNotNull(password1);
    }*/

}