package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.other.Suggestion;
import com.example.springbootfinal.domain.userEntity.Expert;
import com.example.springbootfinal.exception.NotFoundException;
import com.example.springbootfinal.repository.CustomerOrderRepository;
import com.example.springbootfinal.repository.ExpertRepository;
import com.example.springbootfinal.repository.SuggestionRepository;
import com.example.springbootfinal.service.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.springbootfinal.domain.enumurations.StatusOfOrder.WAITING_FOR_EXPERT_SUGGESTIONS;
import static com.example.springbootfinal.domain.enumurations.StatusOfOrder.WAITING_FOR_SELECT_EXPERT;
import static com.example.springbootfinal.service.impl.CustomerOrderServiceImpl.validatePrice;
import static com.example.springbootfinal.service.impl.SubDutyServiceImpl.checkAndRegisterDurationTimeOfWork;
import static com.example.springbootfinal.service.impl.SubDutyServiceImpl.checkAndRegisterTimeOfLoan;

@Service
public class SuggestionServiceImpl implements SuggestionService {

    @Autowired
    SuggestionRepository suggestionRepository;
    @Autowired
    ExpertRepository expertRepository;

    @Autowired
    CustomerOrderRepository customerOrderRepository;

    @Override
    public void sendOfferForSubDuty(Integer expertId, Integer customerOrderId, Double suggestionPrice, String timeOfWork
            , String durationTimeOfWork) throws SQLException {
        Optional<Expert> byId = expertRepository.findById(expertId);
        CustomerOrder customerOrder = customerOrderRepository.findById(customerOrderId).orElseThrow(() -> new NotFoundException("i can not found ir !"));

        Suggestion suggestion = new Suggestion();
        if (byId.isPresent()) {
            Double fixPrice = customerOrder.getProposedPrice();
            Double validatedPrice = validatePrice(suggestionPrice, fixPrice);
            suggestion.setSuggestionPrice(validatedPrice);

            String time = checkAndRegisterTimeOfLoan(timeOfWork);
            suggestion.setTimeOfStartingWork(time);

            suggestion.setTimeOfSendSuggestion(String.valueOf(LocalDate.now()));
            String time1 = checkAndRegisterDurationTimeOfWork(durationTimeOfWork);
            suggestion.setDurationTimeOfWork(time1);
            suggestion.setCustomerOrder(customerOrder);

            suggestionRepository.save(suggestion);
            customerOrder.setStatusOfOrder(WAITING_FOR_SELECT_EXPERT);
            customerOrderRepository.save(customerOrder);
        }
    }


    @Override
    public List<CustomerOrder> customerOrderList() {
        List<CustomerOrder> all = customerOrderRepository.findAll();
        List<CustomerOrder> filteredOrders = all.stream()
                .filter(order ->
                        order.getStatusOfOrder().equals(WAITING_FOR_EXPERT_SUGGESTIONS) ||
                                order.getStatusOfOrder().equals(WAITING_FOR_SELECT_EXPERT)
                )
                .collect(Collectors.toList());
        for (CustomerOrder order : filteredOrders) {
            System.out.println(order);
        }
        return filteredOrders;
    }

    @Override
    public List<Suggestion> findByCustomerIdOrderByProposedPriceDesc(Integer customerOrderId) {
        List<Suggestion> byCustomerOrderIdOrderByProposedPriceDesc = suggestionRepository.findByCustomerOrderIdOrderByProposedPriceDesc(customerOrderId);
        if (byCustomerOrderIdOrderByProposedPriceDesc.isEmpty()) {
            throw new NotFoundException("i can not find this customer order");
        } else {
            for (Suggestion suggestion : byCustomerOrderIdOrderByProposedPriceDesc) {
                System.out.println(suggestion);
            }
        }
        return byCustomerOrderIdOrderByProposedPriceDesc;
    }

    @Override
    public List<Suggestion> findByCustomerOrderIdOrderByExpertStarsDesc(Integer customerOrderId) {
        List<Suggestion> byCustomerOrderIdOrderByExpertStarsDesc =
                suggestionRepository.findByCustomerOrderIdOrderByExpertStarsDesc(customerOrderId);
        if (byCustomerOrderIdOrderByExpertStarsDesc.isEmpty()) {
            throw new NotFoundException("i can not find this customer order");
        } else {
            for (Suggestion suggestion : byCustomerOrderIdOrderByExpertStarsDesc) {
                System.out.println(suggestion);
            }
        }
        return byCustomerOrderIdOrderByExpertStarsDesc;

    }
}
