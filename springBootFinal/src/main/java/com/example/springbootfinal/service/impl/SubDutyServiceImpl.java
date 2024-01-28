package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.enumurations.StatusOfOrder;
import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.serviceEntity.Duty;
import com.example.springbootfinal.domain.serviceEntity.SubDuty;
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
    public void saveOrder(Integer customerId, Integer subServiceId) throws SQLException {
        dutyRepository.findAll().forEach(System.out::println);
        subDutyRepository.findAll().forEach(System.out::println);
        CustomerOrder customerOrder = new CustomerOrder();

        Customer customer = customerRepository.findById(customerId).orElse(null);
        SubDuty subDuty = subDutyRepository.findById(subServiceId).orElse(null);
        String descriptionOfOrder = "you should it ! ";
        double proposedPrice = 8000.00;
        assert subDuty != null;
        Double fixPrice = subDuty.getPrice();
        Double validatedPrice = null;
        if (proposedPrice >= fixPrice) {
            validatedPrice = proposedPrice;
        } else {
            System.out.println("your price is under the lowest price");
        }
        String timeOfWork;
        timeOfWork = checkAndRegisterTimeOfLoan("1402/11/30");
        String address = "mashhad";
        StatusOfOrder waitingForSuggestExpert = StatusOfOrder.WAITING_FOR_SELECT_EXPERT;
        customerOrder.setCustomer(customer);
        customerOrder.setDescriptionOfOrder(descriptionOfOrder);
        customerOrder.setAddress(address);
        customerOrder.setStatusOfOrder(waitingForSuggestExpert);
        customerOrder.setProposedPrice(validatedPrice);
        customerOrder.setSubService(subDuty);
        customerOrder.setTimeOfDoing(timeOfWork);

        customerOrderRepository.save(customerOrder);

    }

    @Override
    public void saveSubDutyByAdmin(Integer dutyId, String subServiceName) {
        Duty duty = dutyRepository.findById(dutyId).orElse(null);

        if (duty == null) {
            System.out.println("i dint have this duty ! ");

        } else {
            Optional<SubDuty> bySubServiceName = subDutyRepository.findBySubServiceName(subServiceName);
            if (bySubServiceName.isPresent()) {
                System.out.println(" i have this subDuty");
            } else {
                SubDuty subDuty = new SubDuty();
                subDuty.setSubServiceName("homeElectronic");
                subDuty.setDescription("ddddddddddddd");
                subDuty.setPrice(250.00);
                subDuty.setService(duty);

                subDutyRepository.save(subDuty);
                System.out.println("subDuty added to data base ! ");

            }
        }
    }

    @Transactional
    @Override
    public void changeDescriptionOfSubDuty(Integer subDutyId, String newDescription) {
        SubDuty subDuty = subDutyRepository.findById(subDutyId).orElse(null);

        if (subDuty != null) {
            subDuty.setDescription(newDescription);
            subDutyRepository.save(subDuty);
        } else {
            System.out.println(" i do not have this id in subDuty !");
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
