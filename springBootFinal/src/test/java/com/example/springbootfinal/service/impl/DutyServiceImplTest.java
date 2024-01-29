package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.serviceEntity.Duty;
import com.example.springbootfinal.repository.AdminRepository;
import com.example.springbootfinal.repository.DutyRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DutyServiceImplTest {

    @Autowired
    private DutyRepository dutyRepository;
    @Autowired
    private DutyServiceImpl dutyService;

    @Test
    @Order(1)
    void saveServiceByAdminTest() {
        String dutyName = "electronic";
        Optional<Duty> byName = dutyRepository.findByName(dutyName);

        dutyService.saveServiceByAdmin(dutyName);

        assertNotNull(dutyName,"it should not be null");
        assertFalse(byName.isPresent());
    }
}