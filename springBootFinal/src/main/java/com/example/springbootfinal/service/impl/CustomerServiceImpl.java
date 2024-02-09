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
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public List<Customer> findAllCustomerByCriteria(Map<String, String> criteria) {
        Specification<Customer> spec = Specification.where(null);

        for (Map.Entry<String, String> entry : criteria.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (value != null && !value.trim().isEmpty()) {
                switch (key) {
                    case "firstname":
                        spec = spec.and(firstNameContains(value));
                        break;
                    case "lastname":
                        spec = spec.and(lastNameContains(value));
                        break;
                    case "email":
                        spec = spec.and(emailEquals(value));
                        break;
                }
            }
        }

        return customerRepository.findAll(spec);
    }

    private Specification<Customer> firstNameContains(String value) {
        return (Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder builder) ->
                builder.like(root.get("firstName"), "%" + value + "%");
    }

    private Specification<Customer> lastNameContains(String value) {
        return (Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder builder) ->
                builder.like(root.get("lastName"), "%" + value + "%");
    }

    private Specification<Customer> emailEquals(String value) {
        return (Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder builder) ->
                builder.equal(root.get("email"), value);
    }

}



