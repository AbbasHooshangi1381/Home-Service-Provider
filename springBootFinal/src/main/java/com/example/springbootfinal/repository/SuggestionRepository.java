package com.example.springbootfinal.repository;

import com.example.springbootfinal.domain.other.Suggestion;
import com.example.springbootfinal.domain.userEntity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SuggestionRepository extends JpaRepository<Suggestion, Integer> {
    @Query("SELECT s FROM Suggestion s " +
            "JOIN FETCH s.customerOrder co " +
            "WHERE co.customer = :customer " +
            "ORDER BY s.suggestionPrice DESC")
    List<Suggestion> showSuggestionOrderByPriceOfSuggestion(@Param("customer") Customer customer);



}
