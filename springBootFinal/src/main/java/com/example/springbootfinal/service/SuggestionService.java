package com.example.springbootfinal.service;

import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.other.Suggestion;

import java.util.List;

public interface SuggestionService  {
    List<Suggestion> findByCustomerIdOrderByProposedPriceDesc(Integer customerOrderId);
    List<Suggestion> findByCustomerOrderIdOrderByExpertStarsDesc(Integer customerOrderId);

}
