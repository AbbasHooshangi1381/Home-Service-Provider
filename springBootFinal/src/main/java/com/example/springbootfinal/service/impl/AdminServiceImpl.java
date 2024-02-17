package com.example.springbootfinal.service.impl;
import com.example.springbootfinal.domain.userEntity.Admin;
import com.example.springbootfinal.exception.NotFoundException;
import com.example.springbootfinal.exception.NotValidException;
import com.example.springbootfinal.repository.*;
import com.example.springbootfinal.service.AdminService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


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
                            SubDutyRepository subDutyRepository, ExpertRepository expertRepository
            , BCryptPasswordEncoder passwordEncoder ) {
        this.adminRepository = adminRepository;
        this.dutyRepository = dutyRepository;
        this.subDutyRepository = subDutyRepository;
        this.expertRepository = expertRepository;
        this.passwordEncoder = passwordEncoder;
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
        if (present) {
            throw new NotValidException(" you have this password in database");
        }
        Admin admin = adminRepository.findByPassword(oldPassword).orElseThrow(() -> new NotFoundException(" i can not found this password"));
        admin.setPassword(newPassword);
        return admin;

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return adminRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("i can not found this email!"));
    }

}
