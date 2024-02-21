package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.enumurations.StatusOfOrder;
import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.other.Suggestion;
import com.example.springbootfinal.domain.userEntity.Customer;
import com.example.springbootfinal.domain.userEntity.Expert;
import com.example.springbootfinal.exception.NotFoundException;
import com.example.springbootfinal.exception.NotValidException;
import com.example.springbootfinal.repository.CustomerOrderRepository;
import com.example.springbootfinal.repository.ExpertRepository;
import com.example.springbootfinal.repository.SuggestionRepository;
import com.example.springbootfinal.service.SuggestionService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.springbootfinal.domain.enumurations.StatusOfOrder.WAITING_FOR_EXPERT_SUGGESTIONS;
import static com.example.springbootfinal.domain.enumurations.StatusOfOrder.WAITING_FOR_SELECT_EXPERT;
import static com.example.springbootfinal.service.impl.CustomerOrderServiceImpl.validatePrice;
import static com.example.springbootfinal.service.impl.SubDutyServiceImpl.checkAndRegisterDurationTimeOfWork;
import static com.example.springbootfinal.service.impl.SubDutyServiceImpl.checkAndRegisterTimeOfLoan;

@Service
@Transactional
public class SuggestionServiceImpl implements SuggestionService {

    SuggestionRepository suggestionRepository;
    ExpertRepository expertRepository;
    CustomerOrderRepository customerOrderRepository;

    public SuggestionServiceImpl(SuggestionRepository suggestionRepository,
                                 ExpertRepository expertRepository, CustomerOrderRepository customerOrderRepository) {
        this.suggestionRepository = suggestionRepository;
        this.expertRepository = expertRepository;
        this.customerOrderRepository = customerOrderRepository;
    }

    @Override
    public void sendSuggestionForOrder(Integer expertId, Integer customerOrderId, Double suggestionPrice, String timeOfWork
            , String durationTimeOfWork) {
        Expert expert = expertRepository.findById(expertId).orElseThrow(() -> new NotFoundException("i can not found ir !"));
        CustomerOrder customerOrder = customerOrderRepository.findById(customerOrderId).orElseThrow(() ->
                new NotFoundException("i can not found ir !"));
        Suggestion suggestion = new Suggestion();
        if (expert != null) {
            StatusOfOrder statusOfOrder = customerOrder.getStatusOfOrder();
            if (statusOfOrder.equals(WAITING_FOR_SELECT_EXPERT) || statusOfOrder.equals(WAITING_FOR_EXPERT_SUGGESTIONS)) {
                Double fixPrice = customerOrder.getProposedPrice();
                Double validatedPrice = validatePrice(suggestionPrice, fixPrice);
                suggestion.setSuggestionPrice(validatedPrice);
                String time = checkAndRegisterTimeOfLoan(timeOfWork);
                suggestion.setTimeOfStartingWork(time);
                suggestion.setTimeOfSendSuggestion(String.valueOf(LocalDate.now()));
                String time1 = checkAndRegisterDurationTimeOfWork(durationTimeOfWork);
                suggestion.setExpert(expert);
                suggestion.setDurationTimeOfWork(time1);
                suggestion.setCustomerOrder(customerOrder);

                suggestionRepository.save(suggestion);
                customerOrder.setStatusOfOrder(WAITING_FOR_SELECT_EXPERT);
                customerOrderRepository.save(customerOrder);
            }
            else {
                throw new NotValidException(" your status of order is not true !");
            }
        }
    }

    @Override
    public List<CustomerOrder> customerOrderList() {
        List<CustomerOrder> all = customerOrderRepository.findAll();
        List<CustomerOrder> filteredOrders = all.stream()
                .filter(order ->
                        order.getStatusOfOrder().equals(WAITING_FOR_EXPERT_SUGGESTIONS) ||
                                order.getStatusOfOrder().equals(WAITING_FOR_SELECT_EXPERT))
                .collect(Collectors.toList());
        for (CustomerOrder order : filteredOrders) {
            System.out.println(order);
        }
        return filteredOrders;
    }

    @Override
    public List<Suggestion> showSuggestionOrderByPriceOfSuggestions(Integer customerOrderId) {
        CustomerOrder customerOrder = customerOrderRepository.findById(customerOrderId).orElseThrow(() ->
                new NotFoundException("Could not find the customer order"));
        Customer customer = customerOrder.getCustomer();
        List<Suggestion> byCustomerOrderIdOrderByProposedPriceDesc =
                suggestionRepository.showSuggestionOrderByPriceOfSuggestion(customer);
        if (byCustomerOrderIdOrderByProposedPriceDesc.isEmpty()) {
            throw new NotFoundException("I cannot find suggestions for this customer order");
        } else {
            for (Suggestion suggestion : byCustomerOrderIdOrderByProposedPriceDesc) {
                System.out.println(suggestion);
            }
        }
        return byCustomerOrderIdOrderByProposedPriceDesc;
    }

    @Override
    public List<Suggestion> showSuggestionOrderByExpertStars(Integer customerOrderId) {
        CustomerOrder customerOrder = customerOrderRepository.findById(customerOrderId).orElseThrow(() ->
                new NotFoundException("I can not found this customer order !"));
        Integer id = customerOrder.getCustomer().getId();
        List<Suggestion> expertsByOrderIdOrderByStarDesc =
                expertRepository.findExpertsByOrderIdOrderByStarDesc(id);
        if (expertsByOrderIdOrderByStarDesc.isEmpty()) {
            throw new NotFoundException("i can not find this customer order");
        } else {
            for (Suggestion suggestion : expertsByOrderIdOrderByStarDesc) {
                System.out.println(suggestion);
                break;
            }
        }
        return expertsByOrderIdOrderByStarDesc;
    }
}
