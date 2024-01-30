package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.userEntity.Admin;
import com.example.springbootfinal.repository.AdminRepository;
import com.example.springbootfinal.repository.DutyRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
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
    void saveAdmin_withEmailRepeat() {
        String validFirstName = "John";
        String validLastName = "Smith";
        String validEmail = "abbas.ali@example.com";
        String validUserName = "johnsmith";
        LocalDate validTimeOfSignIn = LocalDate.now();
        try {
            adminService.saveAdmin(validFirstName, validLastName, validEmail, validUserName, validTimeOfSignIn);
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
        Optional<Admin> byUserNameAndPassword = adminRepository.findByUserNameAndPassword(userName, password);
        assertTrue(byUserNameAndPassword.isPresent());
        Optional<Admin> byUserNameAndPassword1 = adminRepository.findByUserNameAndPassword("aaaa", "bbbb");
        assertFalse(byUserNameAndPassword1.isPresent());
    }

    @Test
    @Transactional
    @Order(4)
    void changePasswordWithAdmin() {
        String email = admins.getEmail();
        Optional<Admin> byEmail = adminRepository.findByEmail(email);
        Integer id = byEmail.get().getId();
        assertNotNull(id);
        String newPassword = "newPassword123";
        Boolean aBoolean = adminService.changePassword(id, newPassword);
        assertTrue(aBoolean);
        String changedPassword = byEmail.get().getPassword();
        assertEquals(newPassword, changedPassword);
    }
}
