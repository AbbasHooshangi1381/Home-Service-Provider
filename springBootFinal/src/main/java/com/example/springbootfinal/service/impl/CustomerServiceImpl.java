package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.enumurations.StatusOfOrder;
import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.userEntity.Admin;
import com.example.springbootfinal.domain.userEntity.Customer;
import com.example.springbootfinal.repository.CustomerOrderRepository;
import com.example.springbootfinal.repository.CustomerRepository;
import com.example.springbootfinal.service.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

import static com.example.springbootfinal.service.impl.AdminServiceImpl.validationEmails;
import static com.example.springbootfinal.service.impl.AdminServiceImpl.validationNames;
import static com.example.springbootfinal.validation.Validation.generateRandomPassword;

@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerOrderRepository customerOrderRepository;




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

    @Override
    public void changeStatusOfOrderByCustomerStarted(Integer orderId) {
        Optional<CustomerOrder> byId = customerOrderRepository.findById(orderId);
        byId.ifPresent(customerOrder -> {
            customerOrder.setStatusOfOrder(StatusOfOrder.STARTED);
            customerOrderRepository.save(customerOrder);
        });
    }

    @Override
    public void changeStatusOfOrderByCustomerToFinish(Integer orderId) {
        Optional<CustomerOrder> byId = customerOrderRepository.findById(orderId);
        byId.ifPresent(customerOrder -> {
            customerOrder.setStatusOfOrder(StatusOfOrder.DONE);
            customerOrderRepository.save(customerOrder);
        });
    }


}

