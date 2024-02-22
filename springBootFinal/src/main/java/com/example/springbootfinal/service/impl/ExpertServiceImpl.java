package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.enumurations.ExpertStatus;
import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.other.ExpertRating;

import com.example.springbootfinal.domain.serviceEntity.SubDuty;
import com.example.springbootfinal.domain.userEntity.Expert;
import com.example.springbootfinal.exception.DuplicateException;
import com.example.springbootfinal.exception.NotFoundException;
import com.example.springbootfinal.repository.*;
import com.example.springbootfinal.service.ExpertService;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@Transactional
@SuppressWarnings("unused")
public class ExpertServiceImpl implements ExpertService {
    ExpertRepository expertRepository;
    CustomerOrderRepository customerOrderRepository;
    SubDutyRepository subDutyRepository;
    SuggestionRepository suggestionRepository;

    public ExpertServiceImpl(ExpertRepository expertRepository, CustomerOrderRepository customerOrderRepository,
                             SubDutyRepository subDutyRepository, SuggestionRepository suggestionRepository) {
        this.expertRepository = expertRepository;
        this.customerOrderRepository = customerOrderRepository;
        this.subDutyRepository = subDutyRepository;
        this.suggestionRepository = suggestionRepository;
    }

    @Override
    public List<CustomerOrder> showOrderToExpert(String username) {
        Expert expert = expertRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("i can not found this expert"));
        List<SubDuty> expertSubDuties = expert.getSubDutyList();
        List<CustomerOrder> ordersForExpert = new ArrayList<>();
        for (SubDuty subDuty : expertSubDuties) {
            List<CustomerOrder> orders = subDuty.getCustomerOrderList();
            ordersForExpert.addAll(orders);
        }
        return ordersForExpert;
    }

    @Override
    public Expert changeStatusOfExpertByAdmin(Integer id) {
        Expert expert1 = expertRepository.findById(id).orElseThrow(() -> new NotFoundException("Expert not found"));
        ExpertStatus expertStatus = expert1.getExpertStatus();
        if (expertStatus.equals(ExpertStatus.CONFIRMED)) {
            throw new DuplicateException("you confirmed this email");
        }
        expert1.setExpertStatus(ExpertStatus.CONFIRMED);
        expertRepository.save(expert1);
        return expert1;
    }


    public List<Expert> findExpertsBySubDuty(String subDutyName) {
        return expertRepository.findAll((root, query, cb) -> {
            Join<Expert, SubDuty> subDutyJoin = root.join("subDutyList");
            return cb.equal(subDutyJoin.get("subServiceName"), subDutyName);
        });
    }

    public ExpertRating findExpertRatings() {
        Integer highestRating = expertRepository.findAll().stream()
                .max(Comparator.comparingInt(Expert::getStars))
                .orElseThrow(NoSuchElementException::new).getStars();
        Integer lowestRating = expertRepository.findAll().stream()
                .min(Comparator.comparingInt(Expert::getStars))
                .orElseThrow(NoSuchElementException::new).getStars();
        return new ExpertRating(highestRating, lowestRating);
    }

    public List<Expert> findAllExpertByCriteria(Map<String, String> param) {
        Specification<Expert> specification = (root, query, cb) -> {
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
        return expertRepository.findAll(specification);
    }




    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return expertRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("i can not found this email!"));
    }


}





