package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.repository.AdminRepository;
import com.example.springbootfinal.repository.DutyRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class DutyServiceImplTest {

    private AdminRepository adminRepository;
    private DutyRepository dutyRepository;

    private AdminServiceImpl adminService;
    private DutyServiceImpl dutyService;

    @Test
    void saveServiceByAdminTest() {
        String dutyName = "electronic";
        boolean isExceptionThrown = false;

        try {
            dutyRepository.existsByName(dutyName);
            dutyService.saveServiceByAdmin(dutyName);
        } catch (RuntimeException e) {
            isExceptionThrown = true;
            assertEquals("خدمت تکراری است.", e.getMessage());
        }

        assertTrue(isExceptionThrown);
    }
}