package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.enumurations.StatusOfOrder;
import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.other.Wallet;
import com.example.springbootfinal.domain.userEntity.Admin;
import com.example.springbootfinal.domain.userEntity.Customer;
import com.example.springbootfinal.exception.DuplicateException;
import com.example.springbootfinal.exception.NotFoundException;
import com.example.springbootfinal.exception.NotValidException;
import com.example.springbootfinal.repository.CustomerOrderRepository;
import com.example.springbootfinal.repository.CustomerRepository;
import com.example.springbootfinal.repository.WalletRepository;
import com.example.springbootfinal.service.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public String changePassword(Integer id, String password) {
        final Customer customer = customerRepository.findById(id).orElseThrow(() -> new NotFoundException(" i can not found this customer"));
        customer.setPassword(password);
        customerRepository.save(customer);
        return "Password changed successfully for customer with ID " + id;

    }

    @Override
    public Optional<Customer> findByUserNameAndPassword(String username, String password) {
         Customer customer = customerRepository.findByUserNameAndPassword(username, password).orElseThrow(() -> new NotFoundException(" i can not found this user"));
        if (customer != null) {
            System.out.println("you are in system ");
        }
        return Optional.ofNullable(customer);
    }

}

