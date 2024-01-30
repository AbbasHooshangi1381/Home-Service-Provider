package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.enumurations.ExpertStatus;
import com.example.springbootfinal.domain.serviceEntity.Duty;
import com.example.springbootfinal.domain.serviceEntity.SubDuty;
import com.example.springbootfinal.domain.userEntity.Admin;
import com.example.springbootfinal.domain.userEntity.Expert;
import com.example.springbootfinal.image.ImageInput;
import com.example.springbootfinal.repository.DutyRepository;
import com.example.springbootfinal.repository.ExpertRepository;
import com.example.springbootfinal.repository.SubDutyRepository;
import com.example.springbootfinal.service.SubDutyService;
import jakarta.transaction.Transactional;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SubDutyServiceImplTest {
    @Autowired
    private DutyRepository dutyRepository;
    @Autowired
    private SubDutyRepository subDutyRepository;
    @Autowired
    private SubDutyService subDutyService;
    @Autowired
    ExpertRepository expertRepository;

    Duty dutys;
    SubDuty subDutys;
    SubDuty subDutyss;
    Expert save;

    @BeforeAll
    void setUpDuty() throws IOException {
        Duty duty = new Duty(
                "electronic");
        dutys = dutyRepository.save(duty);
        SubDuty subDuty = new SubDuty(
                "gaz",
                500.00,
                "dafergergergrgrver",
                dutys);
        subDutys = subDutyRepository.save(subDuty);

        Expert expert = new Expert(
                "ali",
                "ahmadi",
                "okjggk@gmail.com",
                "pojguiu2",
                "aA53@dfr",
                LocalDate.now(),
                ExpertStatus.NEW, ImageInput.uploadProfilePicture("D:\\file of intelli j\\springBootFinal\\" +
                "src\\main\\java\\com\\example\\springbootfinal\\image\\CamScanner 02-14-2022 12.36_2.jpg"));
        save = expertRepository.save(expert);
        assertNotNull(save);
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
        subDutyService.saveSubDutyByAdmin(dutyId, subServiceName, price, description);
    }

    @Test
    @Transactional
    @Order(2)
    void changeDescriptionOfSubDuty() {
        String subServiceName = subDutys.getSubServiceName();
        Optional<SubDuty> subDuty = subDutyRepository.findBySubServiceName(subServiceName);
        if (subDuty.isPresent()) {
            assertNotNull(subDuty);
            Integer id = subDuty.get().getId();
            String newPassword = "newDescription123";
            assertNotNull(newPassword);
            subDutyService.changeDescriptionOfSubDuty(id, newPassword);
            String changedDescription = subDuty.get().getDescription();
            assertNotNull(changedDescription);
            assertEquals(newPassword, changedDescription);
        }
    }
    @Test
    @Transactional
    @Order(3)
    void changePriceOfSubDutyByAdmin() {
        Integer subDutyId = subDutys.getId();
        Double newPrice = 10.99;
        SubDuty subDuty = new SubDuty();
        subDuty.setPrice(newPrice);
        subDutyService.changePriceOfSubDutyByAdmin(subDutyId, newPrice);
        assertEquals(newPrice, subDuty.getPrice());
    }
    @Test
    @Order(4)
    void showSubDuty() {
        List<SubDuty> subDuties = subDutyService.showSubDuty();
        assertNotNull(subDuties);
    }
    @Test
    @Order(5)
    void deleteExpertInSubDutyField() {
        Integer id = subDutys.getId();
        assertNotNull(id);
        subDutyService.deleteExpertInSubDutyField(id);
    }
    @Test
    @Transactional
    @Order(6)
    void registerExpertInOneSubDuty() {
        Integer id = subDutys.getId();
        assertNotNull(id);
        Integer id1 = save.getId();
        Page<Expert> all = expertRepository.findAll(Pageable.ofSize(id1));
        assertNotNull(all);
        subDutyService.registerExpertInOneSubDuty(id1, id);
    }
}

