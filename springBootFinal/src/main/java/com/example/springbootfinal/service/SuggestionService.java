package com.example.springbootfinal.service;

import com.example.springbootfinal.domain.other.Suggestion;

import java.sql.SQLException;
import java.util.List;

public interface SuggestionService  {
    void sendSuggestionForOrder(String userName, Integer customerOrderId,Double suggestionPrice,String timeOfWork, String durationTimeOfWork) throws SQLException;
    List<Suggestion> showSuggestionOrderByPriceOfSuggestions(Integer customerOrderId);
    List<Suggestion> showSuggestionOrderByExpertStars(Integer customerOrderId);
}
