package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.other.Wallet;
import com.example.springbootfinal.domain.userEntity.Customer;
import com.example.springbootfinal.domain.userEntity.Expert;
import com.example.springbootfinal.exception.DuplicateException;
import com.example.springbootfinal.exception.NotFoundException;
import com.example.springbootfinal.repository.CustomerOrderRepository;
import com.example.springbootfinal.repository.CustomerRepository;
import com.example.springbootfinal.repository.WalletRepository;
import com.example.springbootfinal.service.CustomerService;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.example.springbootfinal.validation.Validation.generateRandomPassword;

@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerOrderRepository customerOrderRepository;
    @Autowired
    WalletRepository walletRepository;

    @Override
    public Customer saveCustomer(String firstName, String lastName, String email, String userName) {
        String password = generateRandomPassword();
        LocalDate timeOfSignIn = LocalDate.now();
         Optional<Customer> byEmail = customerRepository.findByEmail(email);
        if (byEmail.isPresent()) {
            throw new DuplicateException("ایمیل تکراری است.");
        }
        Wallet wallet = new Wallet(500.00);
        Wallet save = walletRepository.save(wallet);
        Customer customer = new Customer(firstName, lastName, email, userName, password, timeOfSignIn, save);
        customerRepository.save(customer);
        return customer;
    }
    @Override
    public String changePassword(String oldPassword,String newPassword) {
         Customer customer = customerRepository.findByPassword(oldPassword).orElseThrow(() -> new NotFoundException(" i can not found this password"));
        customer.setPassword(newPassword);
        return "you changed the password";
    }
    @Override
    public Optional<Customer> findByUserNameAndPassword(String username, String password) {
         Customer customer = customerRepository.findByUserNameAndPassword(username, password).orElseThrow(() -> new NotFoundException(" i can not found this user"));
        if (customer != null) {
            System.out.println("you are in system ");
        }
        return Optional.ofNullable(customer);
    }
    public List<Customer> findAllCustomersByCriteria(Map<String, String> param) {
        Specification<Customer> specification = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (param.containsKey("firstName") && param.get("firstName") != null) {
                predicates.add(cb.like(cb.lower(root.get("firstName")), "%" + param.get("firstName").toLowerCase() + "%"));
            }
            if (param.containsKey("lastName") && param.get("lastName") != null) {
                predicates.add(cb.like(cb.lower(root.get("lastName")), "%" + param.get("lastName").toLowerCase() + "%"));
            }
            if (param.containsKey("email") && param.get("email") != null) {
                predicates.add(cb.like(cb.lower(root.get("email")), "%" + param.get("email").toLowerCase() + "%"));
            }
            query.distinct(true);
            return cb.and(predicates.toArray(new Predicate[0]));
        };
        return customerRepository.findAll(specification);
    }
}



