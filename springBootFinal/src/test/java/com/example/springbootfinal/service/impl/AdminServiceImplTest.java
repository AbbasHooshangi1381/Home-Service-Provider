package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.userEntity.Admin;
import com.example.springbootfinal.repository.AdminRepository;
import com.example.springbootfinal.repository.DutyRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class AdminServiceImplTest {

    private AdminRepository adminRepository;
    private DutyRepository dutyRepository;
    private AdminServiceImpl adminService;
    private DutyServiceImpl dutyService;

    public AdminServiceImplTest(AdminRepository adminRepository, DutyRepository dutyRepository, AdminServiceImpl adminService, DutyServiceImpl dutyService) {
        this.adminRepository = adminRepository;
        this.dutyRepository = dutyRepository;
        this.adminService = adminService;
        this.dutyService = dutyService;
    }

    @Test
    void saveAdmin_allValidationsAndDatabaseBehavior() {
        String validFirstName = "John";
        String validLastName = "Smith";
        String validEmail = "john.smith@example.com";
        String validUserName = "johnsmith";
        LocalDate validTimeOfSignIn = LocalDate.now();

         String invalidFirstName = "";
        String invalidLastName = "Doe";
        assertThrows(RuntimeException.class, () -> {
            adminService.saveAdmin(invalidFirstName, validLastName, validEmail, validUserName, validTimeOfSignIn);
        });
        assertThrows(RuntimeException.class, () -> {
            adminService.saveAdmin(validFirstName, invalidLastName, validEmail, validUserName, validTimeOfSignIn);
        });

        String invalidEmail = "invalid-email";
        assertThrows(RuntimeException.class, () -> {
            adminService.saveAdmin(validFirstName, validLastName, invalidEmail, validUserName, validTimeOfSignIn);
        });

        String duplicateEmail = "existing.email@example.com";
        assertThrows(RuntimeException.class, () -> {
            adminService.saveAdmin(validFirstName, validLastName, duplicateEmail, validUserName, validTimeOfSignIn);
        });

        Admin savedAdmin = new Admin();
        savedAdmin.setEmail(validEmail);
        savedAdmin.setFirstName(validFirstName);
        savedAdmin.setLastName(validLastName);
        savedAdmin.setUserName(validUserName);
        savedAdmin.setDateOfSigningIn(validTimeOfSignIn);
        when(adminRepository.save(any(Admin.class))).thenReturn(savedAdmin);

        Admin result = adminService.saveAdmin(validFirstName, validLastName, validEmail, validUserName, validTimeOfSignIn);

        assertSame(savedAdmin, result);
    }

    @Test
    void findByUserNameAndPassword() {
        String userName="fdfdfe";
        String password="dwefewf";
        Optional<Admin> byUserNameAndPassword = adminRepository.findByUserNameAndPassword(userName, password);
        assertTrue(byUserNameAndPassword.isPresent());
        System.out.println("you are in system ");
    }

    @Test
    void changePasswordWithAdmin() {
        Integer id = 1;
        String newPassword = "newPassword123";

        Optional<Admin> optionalAdmin = adminRepository.findById(id);
        assertTrue(optionalAdmin.isPresent());

        adminService.changePassword(id, newPassword);

        String changedPassword = optionalAdmin.get().getPassword();
        assertEquals(newPassword, changedPassword);
    }


}
