package ir.maktabsharif.service.impl;

import ir.maktabsharif.repository.AdminRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class AdminServiceImplTest {

    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private AdminServiceImpl adminService;

    @Test
    void saveAdmin() {
        String firstName = "ali";
        String lastName = "ahmadi";
        String email = "okjggk@gmail.com";
        String userName = "pojguiu2";
        LocalDate timeOfSignIn = LocalDate.now();
    }
}