package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.enumurations.StatusOfOrder;
import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.serviceEntity.Duty;
import com.example.springbootfinal.domain.serviceEntity.SubDuty;
import com.example.springbootfinal.domain.userEntity.Admin;
import com.example.springbootfinal.domain.userEntity.Customer;
import com.example.springbootfinal.repository.CustomerOrderRepository;
import com.example.springbootfinal.repository.CustomerRepository;
import com.example.springbootfinal.repository.DutyRepository;
import com.example.springbootfinal.repository.SubDutyRepository;
import com.example.springbootfinal.service.SubDutyService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@Transactional
public class SubDutyServiceImpl implements SubDutyService {

    DutyRepository dutyRepository;
    SubDutyRepository subDutyRepository;
    CustomerRepository customerRepository;
    CustomerOrderRepository customerOrderRepository;

    public SubDutyServiceImpl(SubDutyRepository subDutyRepository, DutyRepository dutyRepository
            , CustomerRepository customerRepository, CustomerOrderRepository customerOrderRepository) {
        this.subDutyRepository = subDutyRepository;
        this.dutyRepository = dutyRepository;
        this.customerRepository = customerRepository;
        this.customerOrderRepository = customerOrderRepository;
    }

    @Override
    public void saveSubDutyByAdmin(Integer dutyId, SubDuty subDuty) {
        Duty duty = dutyRepository.findById(dutyId).orElse(null);
        String subServiceName = subDuty.getSubServiceName();

        if (duty == null) {
            System.out.println("i dint have this duty ! ");

        } else {
            Optional<SubDuty> bySubServiceName = subDutyRepository.findBySubServiceName(subServiceName);
            if (bySubServiceName.isPresent()) {
                System.out.println(" i have this subDuty");
            } else {
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
    public void showSubDuty() {
        subDutyRepository.findAll().forEach(System.out::println);

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
