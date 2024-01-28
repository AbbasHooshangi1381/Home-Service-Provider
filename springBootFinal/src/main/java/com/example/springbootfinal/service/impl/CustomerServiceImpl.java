package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.userEntity.Admin;
import com.example.springbootfinal.domain.userEntity.Customer;
import com.example.springbootfinal.repository.CustomerOrderRepository;
import com.example.springbootfinal.repository.CustomerRepository;
import com.example.springbootfinal.service.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

import static com.example.springbootfinal.service.impl.AdminServiceImpl.validationEmails;
import static com.example.springbootfinal.service.impl.AdminServiceImpl.validationNames;
import static com.example.springbootfinal.validation.Validation.generateRandomPassword;

@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository;
    CustomerOrderRepository customerOrderRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerOrderRepository customerOrderRepository) {
        this.customerRepository = customerRepository;
        this.customerOrderRepository = customerOrderRepository;
    }


    @Override
    public Customer saveCustomer(String firstName, String lastName, String email, String userName, LocalDate timeOfSignIn) {

        String validatedFirstName = validationNames(firstName);
        String validatedLastName = validationNames(lastName);
        String validatedEmail = validationEmails(email);
        String password = generateRandomPassword();

        Optional<Customer> byEmail = customerRepository.findByEmail(email);

        if (byEmail.isPresent()) {
            throw new RuntimeException("ایمیل تکراری است.");
        }

        Customer customer = new Customer(validatedFirstName, validatedLastName, validatedEmail, userName, password, timeOfSignIn);
        customerRepository.save(customer);
        return customer;
    }


    @Override
    public Boolean changePassword(Integer id, String newPassword) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.setPassword(newPassword);
            customerRepository.save(customer);
            return true;
        } else {
            System.out.println("You cannot change the password. admin with ID " + id + " does not exist.");
            return false;
        }
    }


}

