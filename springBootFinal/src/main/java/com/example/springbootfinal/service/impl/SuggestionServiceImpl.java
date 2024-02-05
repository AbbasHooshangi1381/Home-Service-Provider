package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.other.Suggestion;
import com.example.springbootfinal.exception.NotFoundException;
import com.example.springbootfinal.repository.SuggestionRepository;
import com.example.springbootfinal.service.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuggestionServiceImpl implements SuggestionService {

    @Autowired
    SuggestionRepository suggestionRepository;

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
