package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.enumurations.StatusOfOrder;
import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.other.Suggestion;
import com.example.springbootfinal.domain.serviceEntity.SubDuty;
import com.example.springbootfinal.domain.userEntity.Customer;
import com.example.springbootfinal.domain.userEntity.Expert;
import com.example.springbootfinal.exception.DuplicateException;
import com.example.springbootfinal.exception.NotFoundException;
import com.example.springbootfinal.exception.NotValidException;
import com.example.springbootfinal.repository.*;
import com.example.springbootfinal.service.CustomerOrderService;
import com.example.springbootfinal.speicification.Specifications;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

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
    public CustomerOrder saveOrder(String descriptionOfOrder, Double proposedPrice, String timeOfWork, String address
            , Integer customerId, Integer subDutyId) throws SQLException {
        dutyRepository.findAll().forEach(System.out::println);
        subDutyRepository.findAll().forEach(System.out::println);
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new NotFoundException("Customer not found with ID: " + customerId));
        SubDuty subDuty = subDutyRepository.findById(subDutyId).orElseThrow(() -> new NotFoundException("SubDuty not found with ID: " + subDutyId));
        Double fixPrice = subDuty.getPrice();
        Double validatedPrice = validatePrice(proposedPrice, fixPrice);
        String time = checkAndRegisterTimeOfLoan(timeOfWork);
         StatusOfOrder waitingForExpertSuggestions = StatusOfOrder.WAITING_FOR_EXPERT_SUGGESTIONS;
        CustomerOrder customerOrder = new CustomerOrder(descriptionOfOrder, validatedPrice, time, address, subDuty, customer
                , waitingForExpertSuggestions);

        return customerOrderRepository.save(customerOrder);
    }
    
    @Override
    public void changeStatusOfOrderByCustomerToWaitingToCome(Integer orderId) {
        CustomerOrder customerOrder = customerOrderRepository.findById(orderId).orElseThrow(() -> new NotFoundException(" i can not find customerOrder"));
        if (customerOrder.getStatusOfOrder().equals(StatusOfOrder.WAITING_FOR_COMING_EXPERT)){
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
    public void changeStatusOfOrderByExpertStarted(Integer orderId, LocalDateTime startedTime) {
        CustomerOrder customerOrder = customerOrderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("I cannot find this order"));

        if (customerOrder.getStatusOfOrder() == StatusOfOrder.STARTED) {
            throw new DuplicateException("You already started this order.");
        }

        Suggestion suggestionByExpert = customerOrder.getSuggestionList().stream()
                .filter(suggestion -> suggestion.getExpert() != null)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("No suggestion made by an expert for this order."));

        LocalDateTime suggestionTimeStamp = LocalDateTime.parse(suggestionByExpert.getTimeOfStartingWork(),
                DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));

        if (startedTime.isBefore(suggestionTimeStamp)) {
            throw new NotValidException("Selected time cannot be earlier than the expert's suggestion time.");
        }

        customerOrder.setStatusOfOrder(StatusOfOrder.STARTED);
        customerOrderRepository.save(customerOrder);
    }

    @Override
    @Transactional
    public void changeStatusOfOrderByCustomerToFinish(Integer orderId, String timeOfFinishingWork) {
        CustomerOrder customerOrder = customerOrderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("I cannot find this order"));

        Suggestion suggestionByExpert = customerOrder.getSuggestionList().stream()
                .filter(suggestion -> suggestion.getExpert() != null)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("No suggestion made by an expert for this order."));

        String timeOfStartingWork = suggestionByExpert.getTimeOfStartingWork();

        String durationTimeOfWork = suggestionByExpert.getDurationTimeOfWork();
        LocalDateTime startWorkTime = LocalDateTime.parse(timeOfStartingWork, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        LocalTime durationTime = LocalTime.parse(durationTimeOfWork, DateTimeFormatter.ofPattern("HH:mm:ss"));
        LocalDateTime expectedFinishTime = startWorkTime
                .plusHours(durationTime.getHour())
                .plusMinutes(durationTime.getMinute())
                .plusSeconds(durationTime.getSecond());
        LocalDateTime actualFinishTime = LocalDateTime.parse(timeOfFinishingWork, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        if (actualFinishTime.isBefore(startWorkTime)){
            throw new NotValidException("you should write the true date(after start date)");
        }
        if (actualFinishTime.isAfter(expectedFinishTime)) {
            long delayHours = ChronoUnit.HOURS.between(expectedFinishTime, actualFinishTime);
            Integer ratingReduction = (int) delayHours;
            Expert expert = suggestionByExpert.getExpert();
            Integer currentRating = expert.getStars();
            Integer updatedRating = currentRating - ratingReduction;
            if (updatedRating <= 0) {
                expert.setEnabled(false);
                expert.setStars(updatedRating);
                expertRepository.save(expert);
            } else {
                expert.setStars(updatedRating);
                expertRepository.save(expert);
            }
        }

        if (customerOrder.getStatusOfOrder().equals(StatusOfOrder.DONE)) {
            throw new DuplicateException("you done this work at past");
        }
        customerOrder.setStatusOfOrder(StatusOfOrder.DONE);
        customerOrderRepository.save(customerOrder);
    }

    @Override
    public List<CustomerOrder> findOrdersByExpertUserName(String userName) {
        Expert expert = expertRepository.findByUserName(userName).orElseThrow(() -> new NotFoundException(" i can not found this user"));
         List<CustomerOrder> ordersByExpertId = customerOrderRepository.findOrdersByExpertId(expert.getId());
         if (ordersByExpertId.isEmpty()){
             throw new NotFoundException(" i can not found this order");
         }
         return ordersByExpertId;
    }

    @Override
    public List<CustomerOrder> customerOrderListOfCustomer(Integer id, String statusOfOrder) {
         Customer customer = customerRepository.findById(id).orElseThrow(() -> new NotFoundException("i can not found this user"));

        StatusOfOrder inputStatus = StatusOfOrder.valueOf(statusOfOrder.toUpperCase());
        List<CustomerOrder> customerOrders = customerOrderRepository.customerOrderListOfCustomer(customer.getId(), inputStatus);
        if (customerOrders.isEmpty()){
            throw new NotFoundException(" i can not found this customerOrder");
        }
        return customerOrders;
    }
    @Override
    public List<CustomerOrder> customerOrderListOfExpert(Integer id, String statusOfOrder) {
        Expert expert = expertRepository.findById(id).orElseThrow(() -> new NotFoundException("i can not found this user"));
        StatusOfOrder inputStatus = StatusOfOrder.valueOf(statusOfOrder.toUpperCase());
        List<CustomerOrder> customerOrders = customerOrderRepository.customerOrderListOExpert(expert.getId(), inputStatus);
        if (customerOrders.isEmpty()){
            throw new NotFoundException(" i can not found this customerOrder");
        }
        return customerOrders;
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
    public List<CustomerOrder> orderByCriteria(Map<String, Object> filterParams) {
         Specification<CustomerOrder> customerOrderSpecification = Specifications.orderByCriteria(filterParams);
        return customerOrderRepository.findAll((Sort) customerOrderSpecification);
    }
}
