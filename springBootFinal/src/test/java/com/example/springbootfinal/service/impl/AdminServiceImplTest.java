package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.userEntity.Admin;
import com.example.springbootfinal.repository.AdminRepository;
import com.example.springbootfinal.repository.DutyRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class AdminServiceImplTest {

    @Autowired
    private  AdminRepository adminRepository;
    @Autowired
    private  AdminServiceImpl adminService;

     Admin admins;

    @BeforeAll
    public void setupClass() {
        String validFirstName = "John";
        String validLastName = "Smith";
        String validEmail = "abbas.ali@example.com";
        String validUserName = "johnsmith";
        LocalDate validTimeOfSignIn = LocalDate.now();
        admins = adminService.saveAdmin(validFirstName, validLastName, validEmail, validUserName, validTimeOfSignIn);
    }

    @Test
    @Order(1)
    void saveAdmin_allValidationsAndDatabaseBehavior() {
        assertNotNull(admins);

    }

    @Test
    @Order(2)
    void findByUserNameAndPassword() {
        String email = admins.getEmail();
        Optional<Admin> byEmail = adminRepository.findByEmail(email);
        String password = byEmail.get().getPassword();
        String userName = byEmail.get().getUserName();
        Optional<Admin> byUserNameAndPassword = adminRepository.findByUserNameAndPassword(userName, password);
        assertTrue(byUserNameAndPassword.isPresent());
        System.out.println("you are in system ");
    }

    @Test
    @Order(3)
    void changePasswordWithAdmin() {
        String email = admins.getEmail();
        Optional<Admin> byEmail = adminRepository.findByEmail(email);
        Integer id = byEmail.get().getId();
        assertNotNull(id);
        String newPassword = "newPassword123";

        adminService.changePassword(id, newPassword);

        String changedPassword = byEmail.get().getPassword();
        assertEquals(newPassword, changedPassword);
    }


}
