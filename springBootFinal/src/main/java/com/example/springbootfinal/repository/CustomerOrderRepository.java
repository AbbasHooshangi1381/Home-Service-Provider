package com.example.springbootfinal.repository;

import com.example.springbootfinal.domain.other.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Integer> {

    @Query("SELECT co FROM CustomerOrder co WHERE co.subService.subServiceName = :subServiceName")
    List<CustomerOrder> findBySubServiceName(@Param("subServiceName") String subServiceName);

}
