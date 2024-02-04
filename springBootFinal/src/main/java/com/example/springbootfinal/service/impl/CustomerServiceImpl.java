package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.enumurations.StatusOfOrder;
import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.userEntity.Admin;
import com.example.springbootfinal.domain.userEntity.Customer;
import com.example.springbootfinal.exception.NotFoundException;
import com.example.springbootfinal.exception.NotValidException;
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
    public Customer saveCustomer(String firstName, String lastName, String email, String userName) {
        String validatedFirstName = validationNames(firstName);
        String validatedLastName = validationNames(lastName);
        String validatedEmail = validationEmails(email);
        String password = generateRandomPassword();
        LocalDate timeOfSignIn = LocalDate.now();
        Optional<Customer> byEmail = customerRepository.findByEmail(email);
        if (byEmail.isPresent()) {
            throw new NotValidException("ایمیل تکراری است.");
        }
        Customer customer = new Customer(validatedFirstName, validatedLastName, validatedEmail, userName, password, timeOfSignIn);
        customerRepository.save(customer);
        return customer;
    }

    @Override
    public String changePassword(Integer id, String password) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.setPassword(password);
            customerRepository.save(customer);
            return "Password changed successfully for customer with ID " + id;
        } else {
            return "Failed to change password. customer with ID " + id + " does not exist.";
        }
    }

    @Override
    public Optional<Customer> findByFirstNameAndPassword(String userName, String password) {
        Optional<Customer> byCustomerNameAndPassword = customerRepository.findByFirstNameAndPassword(userName, password);
        if (byCustomerNameAndPassword.isPresent()) {
            System.out.println("you are in system ");
        } else {
            System.out.println("you are not in system ");
        }
        return byCustomerNameAndPassword;
    }

    @Override
    public void changeStatusOfOrderByCustomerStarted(Integer orderId) {
        Optional<CustomerOrder> byId = customerOrderRepository.findById(orderId);
        if (byId.isEmpty()) {
            throw new NotFoundException("i dont have this order ");
        } else {
            byId.ifPresent(customerOrder -> {
                customerOrder.setStatusOfOrder(StatusOfOrder.STARTED);
                customerOrderRepository.save(customerOrder);
            });
        }
    }

    @Override
    public void changeStatusOfOrderByCustomerToFinish(Integer orderId) {
        Optional<CustomerOrder> byId = customerOrderRepository.findById(orderId);
        if (byId.isEmpty()) {
            throw new NotFoundException("i dont have this order ");
        } else {
            byId.ifPresent(customerOrder -> {
                customerOrder.setStatusOfOrder(StatusOfOrder.DONE);
                customerOrderRepository.save(customerOrder);
            });
        }
    }
}

