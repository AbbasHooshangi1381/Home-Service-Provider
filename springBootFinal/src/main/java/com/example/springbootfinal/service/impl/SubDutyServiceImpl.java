package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.serviceEntity.Duty;
import com.example.springbootfinal.domain.serviceEntity.SubDuty;
import com.example.springbootfinal.domain.userEntity.Expert;
import com.example.springbootfinal.exception.DuplicateException;
import com.example.springbootfinal.exception.InvalidDateException;
import com.example.springbootfinal.exception.NotFoundException;
import com.example.springbootfinal.repository.*;
import com.example.springbootfinal.service.SubDutyService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@SuppressWarnings("unused")
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
    public SubDuty saveSubDutyByAdmin(Integer dutyId, String subServiceName, Double priceOfSubDuty, String description) {
        Duty duty = dutyRepository.findById(dutyId).orElse(null);
        if (duty == null) {
            throw new NotFoundException(" i dont this subDuty");
        } else {
            boolean present = subDutyRepository.findBySubServiceName(subServiceName).isPresent();
            if (present){
                throw new DuplicateException("i have this subDuty");
            }

            SubDuty subDuty = new SubDuty();
            subDuty.setSubServiceName(subServiceName);
            subDuty.setPrice(priceOfSubDuty);
            subDuty.setService(duty);
            subDuty.setDescription(description);
            subDutyRepository.save(subDuty);
            System.out.println("subDuty added to database !");
            return subDuty;
        }
    }


    @Override
    public void changeDescriptionOfSubDuty(Integer subDutyId, String newDescription) {
        SubDuty subDuty = subDutyRepository.findById(subDutyId).orElseThrow(() -> new NotFoundException("SubDuty with ID " + subDutyId + " not found"));
        if (subDuty != null) {
            subDuty.setDescription(newDescription);
            subDutyRepository.save(subDuty);
        } else {
            System.out.println("You cannot change the password. admin with ID " + subDutyId + " does not exist.");
        }
    }

    @Override
    public void changePriceOfSubDutyByAdmin(Integer subDutyId, Double newPrice) {
        SubDuty subDuty = subDutyRepository.findById(subDutyId).orElseThrow(() -> new NotFoundException("SubDuty with ID " + subDutyId + " not found"));
        if (subDuty != null) {
            subDuty.setPrice(newPrice);
            subDutyRepository.save(subDuty);
        } else {
            System.out.println(" i do not have this id in subDuty !");
        }
    }

    @Override
    public void registerExpertInOneSubDuty(Integer expertId, Integer subServiceId) {
        SubDuty subDuty = subDutyRepository.findById(subServiceId)
                .orElseThrow(() -> new NotFoundException("expert with ID " + subServiceId + " not found"));

        if (subDuty != null) {
            List<Expert> allById = expertRepository.findAllById(Collections.singleton(expertId));
            if (allById.isEmpty()) {
                throw new NotFoundException("Expert with ID " + expertId + " not found");
            }
            subDuty.setExperts(allById);
            subDutyRepository.save(subDuty);
        }
    }

    @Override
    public void deleteExpertInSubDutyField(Integer subDutyId) {
        SubDuty subDuty = subDutyRepository.findById(subDutyId).orElseThrow(() -> new NotFoundException("SubDuty with ID not found"));
        if (subDuty != null) {
            subDuty.setExperts(null);
            subDutyRepository.save(subDuty);
        }
    }

    @Override
    public List<SubDuty> showSubDutyOfOneDuty(String dutyName) {
        List<SubDuty> subDuties = subDutyRepository.findSubDutyByServiceName(dutyName);
        if (subDuties.isEmpty()) {
            throw new NotFoundException("I cannot find this subDuty and duty");
        }
        return subDuties;
    }

    public static String checkAndRegisterTimeOfLoan(String inputTime) throws SQLException {
        //String inputTime = "1403-01-15 08:30:00";
        LocalDateTime currentTime = LocalDateTime.parse(inputTime, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        LocalDateTime firstStartDate = LocalDateTime.parse("1402-10-27 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        if (currentTime.isAfter(firstStartDate)) {
            return inputTime;
        } else {
            throw new InvalidDateException("You should choose a date after 1402-10-27");
        }
    }

    public static String checkAndRegisterDurationTimeOfWork(String inputTime) throws SQLException {
        LocalTime currentTime = LocalTime.parse(inputTime, DateTimeFormatter.ofPattern("HH:mm:ss"));

        if (currentTime.isBefore(LocalTime.of(7, 0)) || currentTime.isAfter(LocalTime.of(22, 0))) {
            return inputTime;
        } else {
            throw new InvalidDateException("Time should be before 7 AM or after 10 PM");
        }
    }
}
