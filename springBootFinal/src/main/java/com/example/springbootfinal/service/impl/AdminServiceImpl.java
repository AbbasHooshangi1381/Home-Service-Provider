package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.serviceEntity.SubDuty;
import com.example.springbootfinal.domain.userEntity.Admin;
import com.example.springbootfinal.domain.userEntity.Expert;
import com.example.springbootfinal.exception.DuplicateException;
import com.example.springbootfinal.exception.NotFoundException;
import com.example.springbootfinal.exception.NotValidException;
import com.example.springbootfinal.repository.AdminRepository;
import com.example.springbootfinal.repository.DutyRepository;
import com.example.springbootfinal.repository.ExpertRepository;
import com.example.springbootfinal.repository.SubDutyRepository;
import com.example.springbootfinal.service.AdminService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.springbootfinal.validation.RegexValidation.validateEmail;
import static com.example.springbootfinal.validation.RegexValidation.validationName;
import static com.example.springbootfinal.validation.Validation.generateRandomPassword;

@Service
@Transactional
@SuppressWarnings("unused")
public class AdminServiceImpl implements AdminService {
    AdminRepository adminRepository;
    DutyRepository dutyRepository;
    SubDutyRepository subDutyRepository;
    ExpertRepository expertRepository;
    BCryptPasswordEncoder passwordEncoder;

    public AdminServiceImpl(AdminRepository adminRepository, DutyRepository dutyRepository,
                            SubDutyRepository subDutyRepository, ExpertRepository expertRepository,BCryptPasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.dutyRepository = dutyRepository;
        this.subDutyRepository = subDutyRepository;
        this.expertRepository = expertRepository;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public Admin saveAdmin(String firstName, String lastName, String email, String userName) {
        String password = generateRandomPassword();
        String hashCode=passwordEncoder.encode(password);

        LocalDate timeOfSignIn = LocalDate.now();
        if (adminRepository.existsByEmail(email)) {
            throw new DuplicateException("ایمیل تکراری است.");
        }
        Admin admin = new Admin(firstName, lastName, email, userName, hashCode, timeOfSignIn);
        adminRepository.save(admin);
        return admin;
    }

    @Override
    public Optional<Admin> findByUserNameAndPassword(String username, String password) {
        Admin admin = adminRepository.findByUserNameAndPassword(username, password).orElseThrow(() -> new NotFoundException(" i can not found this admin"));
        if (admin != null) {
            System.out.println("you are in system ");
        } else {
            System.out.println("you are not in system ");
        }
        return Optional.ofNullable(admin);
    }

    @Override
    public Admin changePassword(String oldPassword, String newPassword) {
         boolean present = adminRepository.findByPassword(newPassword).isPresent();
         if (present){
             throw new NotValidException(" you have this password in database");
         }
        Admin admin = adminRepository.findByPassword(oldPassword).orElseThrow(() -> new NotFoundException(" i can not found this password"));
        admin.setPassword(newPassword);
        return admin;

    }
}
