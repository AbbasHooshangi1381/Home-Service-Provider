package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.enumurations.StatusOfOrder;
import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.serviceEntity.SubDuty;
import com.example.springbootfinal.domain.userEntity.Customer;
import com.example.springbootfinal.exception.DuplicateException;
import com.example.springbootfinal.exception.NotFoundException;
import com.example.springbootfinal.exception.NotValidException;
import com.example.springbootfinal.repository.*;
import com.example.springbootfinal.service.CustomerOrderService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

import static com.example.springbootfinal.service.impl.SubDutyServiceImpl.checkAndRegisterTimeOfLoan;

@Service
@Transactional
@SuppressWarnings("unused")
public class CustomerOrderServiceImpl implements CustomerOrderService {
    CustomerRepository customerRepository;
    SubDutyRepository subDutyRepository;
    CustomerOrderRepository customerOrderRepository;
    DutyRepository dutyRepository;
    ExpertRepository expertRepository;

    public CustomerOrderServiceImpl(CustomerRepository customerRepository, SubDutyRepository subDutyRepository,
                                    CustomerOrderRepository customerOrderRepository, DutyRepository dutyRepository,
                                    ExpertRepository expertRepository) {
        this.customerRepository = customerRepository;
        this.subDutyRepository = subDutyRepository;
        this.customerOrderRepository = customerOrderRepository;
        this.dutyRepository = dutyRepository;
        this.expertRepository = expertRepository;
    }

    @Override
    public CustomerOrder saveOrder(String descriptionOfOrder, Double proposedPrice, String timeOfWork, String address,
                                   StatusOfOrder waitingForSuggestExpert, Integer customerId, Integer subDutyId) throws SQLException {
        dutyRepository.findAll().forEach(System.out::println);
        subDutyRepository.findAll().forEach(System.out::println);
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new NotFoundException("Customer not found with ID: " + customerId));
        SubDuty subDuty = subDutyRepository.findById(subDutyId).orElseThrow(() -> new NotFoundException("SubDuty not found with ID: " + subDutyId));
        Double fixPrice = subDuty.getPrice();
        Double validatedPrice = validatePrice(proposedPrice, fixPrice);
        String time = checkAndRegisterTimeOfLoan(timeOfWork);
        CustomerOrder customerOrder = new CustomerOrder(descriptionOfOrder, validatedPrice, time, address, subDuty, customer, waitingForSuggestExpert);

        return customerOrderRepository.save(customerOrder);
    }
    
    @Override
    public void changeStatusOfOrderByCustomerToWaitingToCome(Integer orderId) {
        CustomerOrder customerOrder = customerOrderRepository.findById(orderId).orElseThrow(() -> new NotFoundException(" i can not find customerOrder"));
        if (customerOrder.getStatusOfOrder().equals(StatusOfOrder.DONE)){
            throw new DuplicateException("you changed this customer order");
        }
        SubDuty subDuty = customerOrder.getSubService();
        Double proposedPrice = customerOrder.getProposedPrice();
        subDuty.setPrice(proposedPrice);
        customerOrder.setStatusOfOrder(StatusOfOrder.WAITING_FOR_COMING_EXPERT);
        subDutyRepository.save(subDuty);
        customerOrderRepository.save(customerOrder);
    }

    @Override
    public List<CustomerOrder> findOrdersByExpertId(Integer expertId) {
        expertRepository.findById(expertId).orElseThrow(()->new NotFoundException(" I CAN NOT FOUND THIS EXPERT"));
         List<CustomerOrder> ordersByExpertId = customerOrderRepository.findOrdersByExpertId(expertId);
         if (ordersByExpertId.isEmpty()){
             throw new NotFoundException(" i can not found this order");
         }
         return ordersByExpertId;
    }

    public static Double validatePrice(Double proposedPrice, Double fixPrice) {
        Double validatedPrice;
        if (proposedPrice >= fixPrice) {
            validatedPrice = proposedPrice;
        } else {
            throw new NotValidException("your price is under the fix price");
        }
        return validatedPrice;
    }
}
