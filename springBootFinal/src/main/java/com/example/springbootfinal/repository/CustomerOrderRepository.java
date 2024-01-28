package com.example.springbootfinal.repository;

import com.example.springbootfinal.domain.other.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Integer> {

    @Query("SELECT o FROM CustomerOrder o WHERE o.customer.id = :customerId ORDER BY o.proposedPrice DESC")
    List<CustomerOrder> findByCustomerIdOrderByProposedPriceDesc(@Param("customerId") int customerId);

    @Query("SELECT o FROM CustomerOrder o JOIN o.subService.experts e WHERE o.customer.id = :customerId ORDER BY e.stars DESC")
    List<CustomerOrder> findByCustomerIdOrderByExpertStarsDesc(@Param("customerId") int customerId);



}