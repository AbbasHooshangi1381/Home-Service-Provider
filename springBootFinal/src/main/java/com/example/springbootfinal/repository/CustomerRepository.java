package com.example.springbootfinal.repository;

import com.example.springbootfinal.domain.userEntity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

     boolean existsByEmail(String email);

   //  boolean changePassword(Integer id , String newPassword);



}
