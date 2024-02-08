package com.example.springbootfinal.repository;

import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.other.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface SuggestionRepository extends JpaRepository<Suggestion, Integer> {
    @Query("SELECT o FROM Suggestion o WHERE o.customerOrder.id = :customerOrderId ORDER BY o.suggestionPrice DESC")
    List<Suggestion> findAllPriceByCustomerOrderId(@Param("customerOrderId") Integer customerOrderId);

    @Query("SELECT o FROM Suggestion o JOIN o.customerOrder.subService.experts e WHERE" +
            " o.customerOrder.id = :customerOrderId ORDER BY e.stars DESC")
    List<Suggestion> findByCustomerOrderIdOrderByExpertStarsDesc(@Param("customerOrderId") Integer customerOrderId);
}
