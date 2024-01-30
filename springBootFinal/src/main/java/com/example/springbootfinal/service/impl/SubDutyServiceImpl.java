package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.serviceEntity.Duty;
import com.example.springbootfinal.domain.serviceEntity.SubDuty;
import com.example.springbootfinal.domain.userEntity.Expert;
import com.example.springbootfinal.repository.*;
import com.example.springbootfinal.service.SubDutyService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SubDutyServiceImpl implements SubDutyService {
    @Autowired
    DutyRepository dutyRepository;
    @Autowired
    SubDutyRepository subDutyRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerOrderRepository customerOrderRepository;
    @Autowired
    ExpertRepository expertRepository;

    @Override
    public void saveSubDutyByAdmin(Integer dutyId,String subServiceName,Double priceOfSubDuty,String description ) {

        Duty duty = dutyRepository.findById(dutyId).orElse(null);
        if (duty == null) {
            System.out.println("i dint have this duty ! ");

        } else {
            Optional<SubDuty> bySubServiceName = subDutyRepository.findBySubServiceName(subServiceName);
            if (bySubServiceName.isPresent()) {
                System.out.println(" i have this subDuty");
            } else {
                SubDuty subDuty=new SubDuty();
                subDuty.setSubServiceName(subServiceName);
                subDuty.setPrice(priceOfSubDuty);
                subDuty.setService(duty);
                subDuty.setDescription(description);
                subDutyRepository.save(subDuty);
                System.out.println("subDuty added to data base ! ");

            }
        }
    }

    @Override
    public void changeDescriptionOfSubDuty(Integer subDutyId, String newDescription) {
        Optional<SubDuty> optionalSubDuty = subDutyRepository.findById(subDutyId);

        if (optionalSubDuty.isPresent()) {
            SubDuty subDuty = optionalSubDuty.get();
            subDuty.setDescription(newDescription);
            subDutyRepository.save(subDuty);
        } else {
            System.out.println("You cannot change the password. admin with ID " + subDutyId + " does not exist.");
        }
    }

    @Override
    public void changePriceOfSubDutyByAdmin(Integer subDutyId, Double newPrice) {
        SubDuty subDuty = subDutyRepository.findById(subDutyId).orElse(null);

        if (subDuty != null) {
            subDuty.setPrice(newPrice);
            subDutyRepository.save(subDuty);
        } else {
            System.out.println(" i do not have this id in subDuty !");
        }
    }

    @Override
    public List<SubDuty> showSubDuty() {
        Optional<Duty> electronic = dutyRepository.findByName("electronic");
        return electronic.get().getSubServiceList();
    }

    @Override
    public void registerExpertInOneSubDuty(Integer expertId, Integer subServiceId) {
         SubDuty subDuty =subDutyRepository.findById(subServiceId).orElse(null);
         List<Expert> all =expertRepository.findAll();
        if (subDuty!=null){
             subDuty.setExperts(all);
             subDutyRepository.save(subDuty);
         }
    }

    @Override
    public void deleteExpertInSubDutyField(Integer subDutyId) {
        SubDuty subDuty = subDutyRepository.findById(subDutyId).orElse(null);
        if (subDuty != null) {
            subDuty.setExperts(null);
            subDutyRepository.save(subDuty);
        }
    }


    public static String checkAndRegisterTimeOfLoan(String inputTime) throws SQLException {
        LocalDate currentTime = LocalDate.parse(inputTime, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        LocalDate firstStartDate = LocalDate.parse("1402-10-27", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        if (currentTime.isAfter(firstStartDate)) {
            return inputTime;
        } else {
            throw new IllegalArgumentException("You should choose a date after 1402-10-27");
        }
    }


}
