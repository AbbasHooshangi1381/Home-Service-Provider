package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.userEntity.BaseUser;
import com.example.springbootfinal.domain.userEntity.Customer;
import com.example.springbootfinal.domain.userEntity.Expert;
import com.example.springbootfinal.repository.BaseUserRepository;
import com.example.springbootfinal.repository.CustomerRepository;
import com.example.springbootfinal.repository.ExpertRepository;
import com.example.springbootfinal.service.BaseUserService;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BaseUserServiceImpl implements BaseUserService  {

    ExpertRepository expertRepository;
    CustomerRepository customerRepository;

    public BaseUserServiceImpl(ExpertRepository expertRepository, CustomerRepository customerRepository) {
        this.expertRepository = expertRepository;
        this.customerRepository = customerRepository;
    }
    public List<Expert> findAllExpertByCriteria(Map<String, String> param) {
        Specification<Expert> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (param.containsKey("firstname") && param.get("firstname") != null) {
                predicates.add(criteriaBuilder.like(root.get("firstname"), "%" + param.get("firstname") + "%"));
            }
            if (param.containsKey("lastname") && param.get("lastname") != null) {
                predicates.add(criteriaBuilder.like(root.get("lastname"), "%" + param.get("lastname") + "%"));
            }
            if (param.containsKey("email") && param.get("email") != null) {
                predicates.add(criteriaBuilder.equal(root.get("email"), param.get("email")));
            }
            if (param.containsKey("specialistField") && param.get("specialistField") != null) {
                predicates.add(criteriaBuilder.equal(root.join("subServices").get("serviceName"), param.get("specialistField")));
            }
            List<Order> orderList = new ArrayList<>();
            if (param.containsKey("averageScoresOrderBy") && param.get("averageScoresOrderBy") != null) {
                if (param.get("averageScoresOrderBy").equalsIgnoreCase("asc")) {
                    orderList.add(criteriaBuilder.asc(root.get("averageScores")));
                } else if (param.get("averageScoresOrderBy").equalsIgnoreCase("desc")) {
                    orderList.add(criteriaBuilder.desc(root.get("averageScores")));
                }
            }
            if (!orderList.isEmpty()) {
                query.orderBy(orderList);
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        return expertRepository.findAll(specification);
    }
    public List<Customer> findAllCustomersByCriteria(Map<String, String> param) {
        Specification<Customer> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (param.containsKey("firstname") && param.get("firstname") != null) {
                predicates.add(criteriaBuilder.like(root.get("firstname"), "%" + param.get("firstname") + "%"));
            }
            if (param.containsKey("lastname") && param.get("lastname") != null) {
                predicates.add(criteriaBuilder.like(root.get("lastname"), "%" + param.get("lastname") + "%"));
            }
            if (param.containsKey("email") && param.get("email") != null) {
                predicates.add(criteriaBuilder.equal(root.get("email"), param.get("email")));
            }
            if (param.containsKey("subService") && param.get("subService") != null) {
                predicates.add(criteriaBuilder.equal(root.join("subServices").get("subServiceName"), param.get("subServiceName")));
            }
            List<Order> orderList = new ArrayList<>();
            if (param.containsKey("stars") && param.get("stars") != null) {
                if (param.get("stars").equalsIgnoreCase("asc")) {
                    orderList.add(criteriaBuilder.asc(root.get("stars")));
                } else if (param.get("stars").equalsIgnoreCase("desc")) {
                    orderList.add(criteriaBuilder.desc(root.get("stars")));
                }
            }
            if (!orderList.isEmpty()) {
                query.orderBy(orderList);
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        return customerRepository.findAll(specification);
    }
}
