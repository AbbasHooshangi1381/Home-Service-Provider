package com.example.springbootfinal.service;

import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.other.Suggestion;

import java.sql.SQLException;
import java.util.List;

public interface SuggestionService  {
    void sendOfferForSubDuty(Integer expertId, Integer customerOrderId,Double suggestionPrice,String timeOfWork, String durationTimeOfWork) throws SQLException;
    List<CustomerOrder> customerOrderList();
    List<Suggestion> findAllPriceByCustomerOrderId(Integer customerOrderId);
    List<Suggestion> findByCustomerOrderIdOrderByExpertStarsDesc(Integer customerOrderId);

}
