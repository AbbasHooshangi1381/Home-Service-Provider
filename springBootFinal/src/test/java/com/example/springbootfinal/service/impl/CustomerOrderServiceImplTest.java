package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.enumurations.StatusOfOrder;
import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.serviceEntity.Duty;
import com.example.springbootfinal.domain.serviceEntity.SubDuty;
import com.example.springbootfinal.domain.userEntity.Admin;
import com.example.springbootfinal.domain.userEntity.Customer;
import com.example.springbootfinal.repository.CustomerOrderRepository;
import com.example.springbootfinal.repository.CustomerRepository;
import com.example.springbootfinal.repository.DutyRepository;
import com.example.springbootfinal.repository.SubDutyRepository;
import com.example.springbootfinal.service.CustomerOrderService;
import com.example.springbootfinal.service.CustomerService;
import com.example.springbootfinal.service.SubDutyService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;
import java.time.LocalDate;

import static com.example.springbootfinal.service.impl.SubDutyServiceImpl.checkAndRegisterTimeOfLoan;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerOrderServiceImplTest {

    @Autowired
    CustomerOrderRepository customerOrderRepository;
    @Autowired
    SubDutyRepository subDutyRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    DutyRepository dutyRepository;
    @Autowired
    CustomerOrderService customerOrderService;
    @Autowired
    CustomerService customerService;

    private Customer customer;
    private Duty dutys;
    private SubDuty subDuty;

    @BeforeEach
    void setUpCustomer() {

        String validFirstName = "John";
        String validLastName = "Smith";
        String validEmail = "abbas.ali@example.com";
        String validUserName = "johnsmith";
        LocalDate validTimeOfSignIn = LocalDate.now();
        customer = customerService.saveCustomer(validFirstName, validLastName, validEmail, validUserName, validTimeOfSignIn);
    }
    @BeforeEach
    void setUpDuty() {
        Duty duty = new Duty(
                "electronic");
        dutys = dutyRepository.save(duty);
    }
    @BeforeEach
    void setUpSubDuty() {

        SubDuty subDuty = new SubDuty(
                "gaz",
                500.00,
                "dafergergergrgrver",
                dutys);


    }

    @Test
    @Order(1)
    public void saveOrder() throws SQLException {
        dutyRepository.findAll().forEach(System.out::println);
        subDutyRepository.findAll().forEach(System.out::println);

        Integer id = customer.getId();
        Integer id1 = subDuty.getId();
        String descriptionOfOrder = "you should it ! ";
        double proposedPrice = 8000.00;
        String timeOfWork="1402/11/30";
        String address = "mashhad";
        StatusOfOrder waitingForSuggestExpert = StatusOfOrder.WAITING_FOR_SELECT_EXPERT;
        CustomerOrder customerOrder1=new CustomerOrder(descriptionOfOrder,proposedPrice,timeOfWork,address,waitingForSuggestExpert);
        assertNotNull(customerOrder1);
        customerOrderService.saveOrder(customerOrder1,id,id1);

    }

}