package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.userEntity.Admin;
import com.example.springbootfinal.repository.AdminRepository;
import com.example.springbootfinal.repository.DutyRepository;
import com.example.springbootfinal.repository.ExpertRepository;
import com.example.springbootfinal.repository.SubDutyRepository;
import com.example.springbootfinal.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.Optional;
import static com.example.springbootfinal.validation.RegexValidation.validateEmail;
import static com.example.springbootfinal.validation.RegexValidation.validationName;
import static com.example.springbootfinal.validation.Validation.generateRandomPassword;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    AdminRepository adminRepository;
    DutyRepository dutyRepository;
    SubDutyRepository subDutyRepository;
    ExpertRepository expertRepository;

    public AdminServiceImpl(AdminRepository adminRepository , DutyRepository dutyRepository,
                            SubDutyRepository subDutyRepository,ExpertRepository expertRepository) {
        this.adminRepository = adminRepository;
        this.dutyRepository = dutyRepository;
        this.subDutyRepository = subDutyRepository;
        this.expertRepository = expertRepository;
    }

    @Override
    public Admin saveAdmin(String firstName, String lastName, String email, String userName, LocalDate timeOfSignIn) {

        String validatedFirstName = validationNames(firstName);
        String validatedLastName = validationNames(lastName);
        String validatedEmail = validationEmails(email);
        String password=generateRandomPassword();

        if (adminRepository.existsByEmail(email)) {
            throw new RuntimeException("ایمیل تکراری است.");
        }

        Admin admin = new Admin(validatedFirstName, validatedLastName, validatedEmail, userName,password, timeOfSignIn);
        adminRepository.save(admin);
        return admin;
    }

    @Override
    public Optional<Admin> findByUserNameAndPassword(String username, String password) {
        Optional<Admin> byUserNameAndPassword = adminRepository.findByUserNameAndPassword(username, password);
        if (byUserNameAndPassword.isPresent()){
            System.out.println("you are in system ");
        }else {
            System.out.println("you are not in system ");
        }
        return byUserNameAndPassword;
    }


    @Override
    public Boolean changePassword(Integer id, String newPassword) {
        Optional<Admin> optionalAdmin = adminRepository.findById(id);

        if (optionalAdmin.isPresent()) {
            Admin admin = optionalAdmin.get();
            admin.setPassword(newPassword);
            adminRepository.save(admin);
            return true;
        } else {
            System.out.println("You cannot change the password. admin with ID " + id + " does not exist.");
            return false;
        }
    }









    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static String validationNames(String firstName) {
        if (!validationName(firstName)) {
            throw new RuntimeException("Please enter a valid name!");
        }
        return firstName;
    }

    public static String validationEmails(String email) {
        if (!validateEmail(email)) {
            throw new RuntimeException("Please enter a valid email address!");
        }
        return email;
    }
}
