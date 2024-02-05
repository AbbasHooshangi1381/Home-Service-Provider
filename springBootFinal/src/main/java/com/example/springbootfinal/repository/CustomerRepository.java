package com.example.springbootfinal.repository;

import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.userEntity.Admin;
import com.example.springbootfinal.domain.userEntity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
     Optional<Customer> findByEmail(String email);

     Optional<Customer> findByUserNameAndPassword(String username, String password);

}
