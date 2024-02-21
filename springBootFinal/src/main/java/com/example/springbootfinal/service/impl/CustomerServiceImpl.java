package com.example.springbootfinal.service.impl;
import com.example.springbootfinal.domain.userEntity.Customer;
import com.example.springbootfinal.exception.NotFoundException;
import com.example.springbootfinal.exception.NotValidException;
import com.example.springbootfinal.repository.CustomerRepository;
import com.example.springbootfinal.service.CustomerService;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Transactional
@Service
@SuppressWarnings("unused")
public class CustomerServiceImpl implements CustomerService {
    CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public String changePassword(String oldPassword,String newPassword) {
         boolean present = customerRepository.findByPassword(newPassword).isPresent();
        if (present){
            throw new NotValidException(" you have this password in database");
        }
        Customer customer = customerRepository.findByPassword(oldPassword).orElseThrow(() -> new NotFoundException(" i can not found this password"));

        customer.setPassword(newPassword);
        return "you changed the password";
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


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return customerRepository.findByEmail(email).orElseThrow(()->new NotFoundException("i can not found this email!"));
    }
}



