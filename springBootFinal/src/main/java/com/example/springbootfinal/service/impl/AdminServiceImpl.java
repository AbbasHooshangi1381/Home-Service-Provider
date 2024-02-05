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
    public Admin saveAdmin(String firstName, String lastName, String email, String userName) {
        String validatedFirstName = validationNames(firstName);
        String validatedLastName = validationNames(lastName);
        String validatedEmail = validationEmails(email);
        String password=generateRandomPassword();
        LocalDate timeOfSignIn=LocalDate.now();
        if (adminRepository.existsByEmail(email)) {
            throw new DuplicateException("ایمیل تکراری است.");
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
    public Admin changePassword(Integer id, String newPassword) {
        Optional<Admin> optionalAdmin = adminRepository.findById(id);
        if (optionalAdmin.isPresent()) {
            Admin admin = optionalAdmin.get();
            admin.setPassword(newPassword);
            adminRepository.save(admin);
            return admin;
        } else {
            throw new NotFoundException("i dont have this admin");
        }
    }

//    @Override
//    public void addingSubDutyToExpert(Integer expertId, Integer subDutyId) {
//        Expert expert = expertRepository.findById(expertId).orElseThrow(() -> new NotFoundException("Expert not found with id: " + expertId));
//        SubDuty subDuty = subDutyRepository.findById(subDutyId).orElseThrow(() -> new NotFoundException("SubDuty not found with id: " + subDutyId));
//        if (subDuty.getExperts()==null){
//            List<Expert> expertList=new ArrayList<>();
//            expertList.add(expert);
//            subDuty.setExperts(expertList);
//        }else {
//            List<Expert>expertLists=subDuty.getExperts();
//            expertLists.add(expert);
//        }
//        subDutyRepository.save(subDuty);
//    }
//
//    @Override
//    public void deletingSubDutyToExpert(Integer expertId, Integer subDutyId)  {
//        Expert expert = expertRepository.findById(expertId).orElseThrow(() -> new NotFoundException("Expert not found with id: " + expertId));
//        SubDuty subDuty = subDutyRepository.findById(subDutyId).orElseThrow(() -> new NotFoundException("SubDuty not found with id: " + subDutyId));
//        if (expert==null||subDuty==null){
//            System.out.println("the expert or subDuty is null !");
//        }
//        assert subDuty != null;
//        if (subDuty.getExperts()!=null){
//             List<Expert> experts = subDuty.getExperts();
//             subDuty.setExperts(null);
//            System.out.println("deleted");
//        }
//        subDutyRepository.save(subDuty);
//    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static String validationNames(String firstName) {
        if (!validationName(firstName)) {
            throw new NotValidException("name not valid ! ");
        }
        return firstName;
    }

    public static String validationEmails(String email) {
        if (!validateEmail(email)) {
            throw new NotValidException("Please enter a valid email address!");
        }
        return email;
    }
}
