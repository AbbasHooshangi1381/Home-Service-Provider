package com.example.springbootfinal.service.impl;
import com.example.springbootfinal.exception.NotFoundException;
import com.example.springbootfinal.repository.*;
import com.example.springbootfinal.service.AdminService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@SuppressWarnings("unused")

public class AdminServiceImpl implements AdminService {
    AdminRepository adminRepository;
    DutyRepository dutyRepository;
    SubDutyRepository subDutyRepository;
    ExpertRepository expertRepository;
    BCryptPasswordEncoder passwordEncoder;
    BaseUserRepository baseUserRepository;

    public AdminServiceImpl(AdminRepository adminRepository, DutyRepository dutyRepository,
                            SubDutyRepository subDutyRepository, ExpertRepository expertRepository
            , BCryptPasswordEncoder passwordEncoder,BaseUserRepository baseUserRepository) {
        this.adminRepository = adminRepository;
        this.dutyRepository = dutyRepository;
        this.subDutyRepository = subDutyRepository;
        this.expertRepository = expertRepository;
        this.passwordEncoder = passwordEncoder;
        this.baseUserRepository = baseUserRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return adminRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("i can not found this email!"));
    }

}
