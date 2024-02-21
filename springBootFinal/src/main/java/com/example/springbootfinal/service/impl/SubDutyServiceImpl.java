package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.serviceEntity.Duty;
import com.example.springbootfinal.domain.serviceEntity.SubDuty;
import com.example.springbootfinal.domain.userEntity.Expert;
import com.example.springbootfinal.exception.DuplicateException;
import com.example.springbootfinal.exception.InvalidDateException;
import com.example.springbootfinal.exception.NotFoundException;
import com.example.springbootfinal.repository.*;
import com.example.springbootfinal.service.SubDutyService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@SuppressWarnings("unused")
public class SubDutyServiceImpl implements SubDutyService {

    DutyRepository dutyRepository;
    SubDutyRepository subDutyRepository;
    CustomerRepository customerRepository;
    CustomerOrderRepository customerOrderRepository;
    ExpertRepository expertRepository;

    public SubDutyServiceImpl(DutyRepository dutyRepository, SubDutyRepository subDutyRepository,
                              CustomerRepository customerRepository, CustomerOrderRepository customerOrderRepository,
                              ExpertRepository expertRepository) {
        this.dutyRepository = dutyRepository;
        this.subDutyRepository = subDutyRepository;
        this.customerRepository = customerRepository;
        this.customerOrderRepository = customerOrderRepository;
        this.expertRepository = expertRepository;
    }

    @Override
    public SubDuty saveSubDutyByAdmin(Integer dutyId, String subServiceName, Double priceOfSubDuty, String description) {
        Duty duty = dutyRepository.findById(dutyId).orElse(null);
        if (duty == null) {
            throw new NotFoundException(" i dont this Duty");
        } else {
            Optional<SubDuty> bySubServiceName = subDutyRepository.findBySubServiceName(subServiceName);
            if (subServiceName.isEmpty()) {
                SubDuty subDuty = new SubDuty();
                subDuty.setSubServiceName(subServiceName);
                subDuty.setPrice(priceOfSubDuty);
                subDuty.setService(duty);
                subDuty.setDescription(description);
                subDutyRepository.save(subDuty);
                System.out.println("subDuty added to database !");
                return subDuty;

            } else {
                throw new DuplicateException("i have this sub service");
            }
        }

    }


    @Override
    public void changeDescriptionOfSubDuty(Integer subDutyId, String newDescription) {
        SubDuty subDuty = subDutyRepository.findById(subDutyId).orElseThrow(() ->
                new NotFoundException("SubDuty with ID " + subDutyId + " not found"));
        if (subDuty != null) {
             boolean present = subDutyRepository.findByDescription(newDescription).isPresent();
             if (present){
                 throw new DuplicateException("i have this description");
             }
            subDuty.setDescription(newDescription);
            subDutyRepository.save(subDuty);
        } else {
            System.out.println("You cannot change the password. admin with ID " + subDutyId + " does not exist.");
        }
    }

    @Override
    public void changePriceOfSubDutyByAdmin(Integer subDutyId, Double newPrice) {
        SubDuty subDuty = subDutyRepository.findById(subDutyId).orElseThrow(() ->
                new NotFoundException("SubDuty with ID " + subDutyId + " not found"));
        if (subDuty != null) {
             boolean present = subDutyRepository.findByPrice(newPrice).isPresent();
             if (present){
                 throw new DuplicateException("i have this price now");
             }
            subDuty.setPrice(newPrice);
            subDutyRepository.save(subDuty);
        } else {
            System.out.println(" i do not have this id in subDuty !");
        }
    }

    @Override
    public void registerExpertInOneSubDuty(Integer expertId, Integer subServiceId) {
        SubDuty subDuty = subDutyRepository.findById(subServiceId)
                .orElseThrow(() -> new NotFoundException("SubDuty with ID " + subServiceId + " not found"));

        Expert expert = expertRepository.findById(expertId)
                .orElseThrow(() -> new NotFoundException("Expert with ID " + expertId + " not found"));

        List<Expert> existingExperts = subDuty.getExperts();

        if (existingExperts == null) {
            existingExperts = new ArrayList<>();
        }

        existingExperts.add(expert);
        subDuty.setExperts(existingExperts);
        subDutyRepository.save(subDuty);
    }

    @Override
    public void deleteExpertInSubDutyField(Integer expertId, Integer subDutyId) {
            SubDuty subDuty = subDutyRepository.findById(subDutyId).orElseThrow(() ->
                    new NotFoundException("SubDuty with ID not found"));
             List<Expert> experts = subDuty.getExperts();
        if (!experts.isEmpty()) {
            experts.removeIf(expert -> Objects.equals(expert.getId(), expertId));
            subDutyRepository.save(subDuty);

        } else {
            throw new NotFoundException("you edited it in past !");
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

    public static String checkAndRegisterTimeOfLoan(String inputTime){
        //String inputTime = "1403-01-15 08:30:00";
        LocalDateTime currentTime = LocalDateTime.parse(inputTime, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        LocalDateTime firstStartDate = LocalDateTime.parse("1402-11-22 00:00:00",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        if (currentTime.isAfter(firstStartDate)) {
            return inputTime;
        } else {
            throw new InvalidDateException("You should choose a date after 1402-11-22");
        }
    }

    public static String checkAndRegisterDurationTimeOfWork(String inputTime) {
        LocalTime currentTime = LocalTime.parse(inputTime, DateTimeFormatter.ofPattern("HH:mm:ss"));

        if (currentTime.isBefore(LocalTime.of(7, 0)) || currentTime.isAfter(LocalTime.of(22, 0))) {
            return inputTime;
        } else {
            throw new InvalidDateException("Time should be before 7 AM or after 10 PM");
        }
    }
}
