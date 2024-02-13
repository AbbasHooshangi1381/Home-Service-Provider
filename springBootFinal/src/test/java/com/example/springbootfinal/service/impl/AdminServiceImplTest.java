
package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.enumurations.ExpertStatus;
import com.example.springbootfinal.domain.serviceEntity.Duty;
import com.example.springbootfinal.domain.serviceEntity.SubDuty;
import com.example.springbootfinal.domain.userEntity.Admin;
import com.example.springbootfinal.domain.userEntity.Expert;
import com.example.springbootfinal.image.ImageInput;
import com.example.springbootfinal.repository.AdminRepository;
import com.example.springbootfinal.repository.DutyRepository;
import com.example.springbootfinal.repository.ExpertRepository;
import com.example.springbootfinal.repository.SubDutyRepository;
import com.example.springbootfinal.service.SubDutyService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AdminServiceImplTest {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private AdminServiceImpl adminService;
    @Autowired
    DutyRepository dutyRepository;
    @Autowired
    SubDutyRepository subDutyRepository;
    @Autowired
    ExpertRepository expertRepository;


    Admin admins;
    Expert save;
    Duty dutys;
    SubDuty subDutys;

    @BeforeAll
    public void setupClass() throws IOException {
        String validFirstName = "John";
        String validLastName = "Smith";
        String validEmail = "abbas.ali@example.com";
        String validUserName = "johnsmith";
        admins = adminService.saveAdmin(validFirstName, validLastName, validEmail, validUserName);

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
/*                "ali",
                "ahmadi",
                "okjggk@gmail.com",
                "pojguiu2",
                "aA53@dfr",
                LocalDate.now(),
                ExpertStatus.NEW, ImageInput.uploadProfilePicture("D:\\file of intelli j\\springBootFinal\\" +
                "src\\main\\java\\com\\example\\springbootfinal\\image\\CamScanner 02-14-2022 12.36_2.jpg")*/);
        save = expertRepository.save(expert);
        assertNotNull(save);
    }

    @Test
    @Order(1)
    void saveAdmin_allValidations() {
        assertNotNull(admins);
    }

    @Test
    @Order(2)
    void saveAdmin_withEmailRepeat() {
        String validFirstName = "John";
        String validLastName = "Smith";
        String validEmail = "abbas.ali@example.com";
        String validUserName = "johnsmith";
        LocalDate validTimeOfSignIn = LocalDate.now();
        try {
            adminService.saveAdmin(validFirstName, validLastName, validEmail, validUserName);
            fail("متد قرار نبود عملیات سیو با ایمیل تکراری را انجام دهد.");
        } catch (RuntimeException e) {
            assertEquals("ایمیل تکراری است.", e.getMessage());
        }
    }

    @Test
    @Order(3)
    void findByUserNameAndPassword() {
        String email = admins.getEmail();
        Admin admin = adminRepository.findByEmail(email).get();
        String password = admin.getPassword();
        assertNotNull(password);
        String userName = admin.getUserName();
        assertNotNull(userName);
        Optional<Admin> byUserNameAndPassword = adminService.findByUserNameAndPassword(userName, password);
        assertTrue(byUserNameAndPassword.isPresent());
        Optional<Admin> byUserNameAndPassword1 = adminService.findByUserNameAndPassword("aaaa", "bbbb");
        assertFalse(byUserNameAndPassword1.isPresent());
    }

/*    @Test
    @Transactional
    @Order(4)
    void changePasswordWithAdmin() {
        String email = admins.getEmail();
        Optional<Admin> byEmail = adminRepository.findByEmail(email);
        Integer id = byEmail.get().getId();
        assertNotNull(id);
        String newPassword = "newPassword123";
         Admin admin = adminService.changePassword(id, newPassword);
        assertNotNull(admin);
        String changedPassword = byEmail.get().getPassword();
        assertEquals(newPassword, changedPassword);
    }*/

/*    @Test
    @Order(5)
    void addingSubDutyToExpert() {
        Integer id = save.getId();
        assertNotNull(id);
        Integer id1 = subDutys.getId();
        assertNotNull(id1);
        adminService.addingSubDutyToExpert(id, id1);
    }

    @Test
    @Order(6)
    void deletingSubDutyToExpert() {
        Integer id = save.getId();
        assertNotNull(id);
        Integer id1 = subDutys.getId();
        assertNotNull(id1);
        adminService.deletingSubDutyToExpert(id, id1);
    }*/
}

