package com.example.springbootfinal.repository;


import com.example.springbootfinal.domain.userEntity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository

public interface CustomerRepository extends JpaRepository<Customer,Integer> , JpaSpecificationExecutor<Customer> {
     Optional<Customer> findByEmail(String email);
     Optional<Customer> findByPassword(String password);
     Optional<Customer> findByUserName(String userName);

}
