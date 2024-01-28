package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.userEntity.Customer;
import com.example.springbootfinal.repository.CustomerOrderRepository;
import com.example.springbootfinal.repository.CustomerRepository;
import com.example.springbootfinal.service.CustomerService;
import com.example.springbootfinal.validation.Validation;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository;
    CustomerOrderRepository customerOrderRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository ,CustomerOrderRepository customerOrderRepository) {
        this.customerRepository = customerRepository;
        this.customerOrderRepository=customerOrderRepository;
    }


    @Override
    public void SaveCustomer() {
        String FirstName = "abbas";
        String validatedFirstName = AdminServiceImpl.validationNames(FirstName);
        String LastName = "hooshangi";
        String validatedLastName = AdminServiceImpl.validationNames(LastName);
        String newEmailOFExpert = null;
        String emailOfExpert = "basjfryan@gmail.com";
        String validatedEmail = AdminServiceImpl.validationEmails(emailOfExpert);
        boolean byEmail = customerRepository.existsByEmail(validatedEmail);
        if (byEmail==true) {
            System.out.println("i have this email");

        } else {
            newEmailOFExpert = validatedEmail;
        }
        String userName = "alfef526";
        String password = Validation.generateRandomPassword();
        LocalDate timeOfSignIn = LocalDate.now();
        Customer customer = new Customer(validatedFirstName, validatedLastName, newEmailOFExpert,
                userName, password, timeOfSignIn);

        customerRepository.save(customer);

    }



    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public Boolean changePassword(Integer id, String newPassword) {
         Customer customer1 = customerRepository.findById(id).orElse(null);

        if (customer1!=null) {
            customer1.setPassword(newPassword);
            customerRepository.save(customer1);
            return true;
        } else {
            System.out.println("You cannot change the password. customer with ID " + id + " does not exist.");
            return false;
        }
    }


    }

