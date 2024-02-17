package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.enumurations.ExpertStatus;
import com.example.springbootfinal.domain.enumurations.Role;
import com.example.springbootfinal.domain.other.Wallet;
import com.example.springbootfinal.domain.userEntity.Admin;
import com.example.springbootfinal.domain.userEntity.BaseUser;
import com.example.springbootfinal.domain.userEntity.Customer;
import com.example.springbootfinal.domain.userEntity.Expert;
import com.example.springbootfinal.email.security.token.ConfirmationToken;
import com.example.springbootfinal.email.security.token.ConfirmationTokenService;
import com.example.springbootfinal.exception.DuplicateException;
import com.example.springbootfinal.image.ImageInput;
import com.example.springbootfinal.repository.*;
import com.example.springbootfinal.service.RegistrationServices;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@SuppressWarnings("unused")

public class RegistrationServiceImp implements RegistrationServices {

    BCryptPasswordEncoder passwordEncoder;
    AdminRepository adminRepository;
    CustomerRepository customerRepository;
    WalletRepository walletRepository;
    ExpertRepository expertRepository;
    BaseUserRepository baseUserRepository;
    ConfirmationTokenService confirmationTokenService;

    public RegistrationServiceImp(BCryptPasswordEncoder passwordEncoder, AdminRepository adminRepository,
                                  CustomerRepository customerRepository, WalletRepository walletRepository,
                                  ExpertRepository expertRepository, BaseUserRepository baseUserRepository,
                                  ConfirmationTokenService confirmationTokenService) {
        this.passwordEncoder = passwordEncoder;
        this.adminRepository = adminRepository;
        this.customerRepository = customerRepository;
        this.walletRepository = walletRepository;
        this.expertRepository = expertRepository;
        this.baseUserRepository = baseUserRepository;
        this.confirmationTokenService = confirmationTokenService;
    }

    @Override
    public String saveAdmin(String firstName, String lastName, String email, String userName, String password) {

        boolean userExists = baseUserRepository.findByEmail(email).isPresent();
        if (userExists) {

            BaseUser baseUser = baseUserRepository.findByEmail(email).get();
            Boolean isEnabled = baseUser.getEnabled();

            if (!isEnabled) {
                String token = UUID.randomUUID().toString();

                saveConfirmationToken(baseUser, token);

                return token;
            }
            throw new DuplicateException(String.format("User with email %s already exists and able!", email));
        }
        String encodedPassword = passwordEncoder.encode(password);

        LocalDate timeOfSignIn = LocalDate.now();
        Boolean enabled = false;
        String token = UUID.randomUUID().toString();
        Admin admin = new Admin(firstName, lastName, email, userName, encodedPassword, timeOfSignIn, enabled, Role.ROLE_ADMIN);
        adminRepository.save(admin);
        saveConfirmationToken(admin, token);
        return token;
    }

    @Override
    public String saveCustomer(String firstName, String lastName, String email, String userName, String password) {
        boolean userExists = baseUserRepository.findByEmail(email).isPresent();
        if (userExists) {
            BaseUser baseUser = baseUserRepository.findByEmail(email).get();
            Boolean isEnabled = baseUser.getEnabled();

            if (!isEnabled) {
                String token = UUID.randomUUID().toString();
                saveConfirmationToken(baseUser, token);
                return token;
            }
            throw new DuplicateException(String.format("User with email %s already exists and able!", email));
        }
        String encodedPassword = passwordEncoder.encode(password);

        LocalDate timeOfSignIn = LocalDate.now();
        Boolean enabled = false;
        Wallet wallet = new Wallet(500.00);
        Wallet save = walletRepository.save(wallet);
        String token = UUID.randomUUID().toString();
        Customer customer = new Customer(firstName, lastName, email, userName, encodedPassword, timeOfSignIn, save, enabled, Role.ROLE_CUSTOMER);
        Customer customer2 = customerRepository.save(customer);
        saveConfirmationToken(customer2, token);
        return "ok!";
    }

    @Override
    public String saveExpert(String firstName, String lastName, String email, String userName, File file, String password) throws IOException {
        boolean userExists = baseUserRepository.findByEmail(email).isPresent();
        if (userExists) {
            BaseUser baseUser = baseUserRepository.findByEmail(email).get();
            Boolean isEnabled = baseUser.getEnabled();
            if (!isEnabled) {
                String token = UUID.randomUUID().toString();
                saveConfirmationToken(baseUser, token);
                return token;
            }
            throw new DuplicateException(String.format("User with email %s already exists and able!", email));
        }
        String encodedPassword = passwordEncoder.encode(password);
        String token = UUID.randomUUID().toString();
        Wallet wallet = new Wallet(500.00);
        Wallet save = walletRepository.save(wallet);
        byte[] imageData = ImageInput.uploadProfilePicture(file);
        ExpertStatus expertStatus = ExpertStatus.NEW;
        Integer star = 0;
        LocalDate timeOfSignIn = LocalDate.now();
        Boolean enabled = false;
        Expert expert = new Expert(firstName, lastName, email, userName, encodedPassword, timeOfSignIn, imageData
                , star, expertStatus, save, enabled, Role.ROLE_EXPERT);
        expertRepository.save(expert);

        saveConfirmationToken(expert, token);
        return token;
    }

    private void saveConfirmationToken(BaseUser baseUser, String token) {
        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15), baseUser);
        confirmationTokenService.saveConfirmationToken(confirmationToken);
    }

    @Override
    public int enableAppUser(String email) {
        return baseUserRepository.enableAppUser(email);
    }
}
