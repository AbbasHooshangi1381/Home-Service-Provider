package com.example.springbootfinal.repository;

import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.other.Suggestion;
import com.example.springbootfinal.domain.userEntity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface SuggestionRepository extends JpaRepository<Suggestion, Integer> {
    @Query("SELECT s FROM Suggestion s " +
            "JOIN FETCH s.customerOrder co " +
            "WHERE co.customer = :customer " +
            "ORDER BY s.suggestionPrice desc ")
    List<Suggestion> showCustomerOrderOfSpecificCustomerBasedOnPriceOfSuggestions(@Param("customer") Customer customer);}
