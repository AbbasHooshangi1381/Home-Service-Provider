package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.serviceEntity.Duty;
import com.example.springbootfinal.domain.serviceEntity.SubDuty;
import com.example.springbootfinal.domain.userEntity.Admin;
import com.example.springbootfinal.repository.DutyRepository;
import com.example.springbootfinal.repository.SubDutyRepository;
import com.example.springbootfinal.service.SubDutyService;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SubDutyServiceImplTest {
    @Autowired
    private DutyRepository dutyRepository;

    @Autowired
    private SubDutyRepository subDutyRepository;

    @Autowired
    private SubDutyService subDutyService;

    Duty dutys;
    SubDuty subDutys;

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

        subDutys=subDutyRepository.save(subDuty);


    }

    @Test
    @Order(1)
    void saveSubDutyByAdminWhenDutyExistsAndSubDutyDoesNotExist() {
        Integer dutyId = dutys.getId();
        Duty duty = dutyRepository.findById(dutyId).orElse(null);
        assertNotNull(duty);
        SubDuty subDuty1 = subDutyRepository.findBySubServiceName("bargh").orElse(null);
        assertNull(subDuty1);

        String subServiceName = "gaz";
        Double price = 500.00;
        String description = "dafergergergrgrver";

        SubDuty subDuty = new SubDuty();
        subDuty.setService(duty);
        subDuty.setPrice(price);
        subDuty.setDescription(description);
        subDuty.setSubServiceName(subServiceName);
        assertNotNull(subDuty);
        subDutyService.saveSubDutyByAdmin(dutyId, subDuty);


    }

    @Test
    void changeDescriptionOfSubDuty() {
        Optional<SubDuty> subDuty = subDutyRepository.findBySubServiceName("gaz");
        assertNotNull(subDuty);
        Integer id = subDuty.get().getId();

        String newPassword = "newDescription123";
        assertNotNull(newPassword);
        subDutyService.changeDescriptionOfSubDuty(id, newPassword);

        String changedDescription= subDuty.get().getDescription();
        assertNotNull(changedDescription);
        assertEquals(newPassword, changedDescription);
    }

    @Test
    void changePriceOfSubDutyByAdmin() {
        Integer subDutyId = subDutys.getId();
        Double newPrice = 10.99;

        SubDuty subDuty = new SubDuty();
        subDuty.setPrice(newPrice);

        subDutyService.changePriceOfSubDutyByAdmin(subDutyId, newPrice);

        assertEquals(newPrice, subDuty.getPrice());

    }

}

