package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.enumurations.StatusOfOrder;
import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.other.Suggestion;
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
import com.example.springbootfinal.service.SuggestionService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static com.example.springbootfinal.service.impl.SubDutyServiceImpl.checkAndRegisterTimeOfLoan;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
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
    @Autowired
    SuggestionService suggestionService;

    private Customer customer;
    private Duty dutys;
    private SubDuty subDutys;
    private CustomerOrder customerOrders;

    @BeforeAll
    void setUpCustomer() throws SQLException {
        String validFirstName = "John";
        String validLastName = "Smith";
        String validEmail = "abbas.ali@example.com";
        String validUserName = "johnsmith";
        LocalDate validTimeOfSignIn = LocalDate.now();
        customer = customerService.saveCustomer(validFirstName, validLastName, validEmail, validUserName);

        Duty duty = new Duty(
                "electronic");
        dutys = dutyRepository.save(duty);

        SubDuty subDuty = new SubDuty(
                "gaz",
                500.00,
                "dafergergergrgrver",
                dutys);
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
        customerOrders = customerOrderService.saveOrder(descriptionOfOrder,
                proposedPrice, timeOfWork, address, waitingForSuggestExpert, id, id1);
    }

    @Test
    @Order(1)
    void findByCustomerIdOrderByProposedPriceDesc() {
        Integer id = customer.getId();
        assertNotNull(id);
        List<Suggestion> byCustomerIdOrderByProposedPriceDesc =
                suggestionService.findAllPriceByCustomerOrderId(id);
        assertNotNull(byCustomerIdOrderByProposedPriceDesc);
    }

    @Test
    @Order(2)
    void findByCustomerIdOrderByExpertStarsDesc() {
        Integer id = customer.getId();
        assertNotNull(id);
        final List<Suggestion> byCustomerOrderIdOrderByExpertStarsDesc = suggestionService.findByCustomerOrderIdOrderByExpertStarsDesc(id);
        assertNotNull(byCustomerOrderIdOrderByExpertStarsDesc);
    }

    @Test
    @Order(3)
    void changeStatusOfOrderByCustomerToWaitingToCome() {
        Integer id = customerOrders.getId();
        assertNotNull(id);
        customerOrderService.changeStatusOfOrderByCustomerToWaitingToCome(id);
    }

    @Test
    @Order(4)
    void showAllOrderBySubDutyName() {
        String subServiceName = subDutys.getSubServiceName();
        assertNotNull(subServiceName);
        List<CustomerOrder> customerOrderOfOneSubDuty = customerOrderService.findCustomerOrderOfOneSubDuty(subServiceName);
        assertNotNull(customerOrderOfOneSubDuty);

    }
}