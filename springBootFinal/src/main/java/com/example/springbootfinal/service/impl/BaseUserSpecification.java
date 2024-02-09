package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.enumurations.GlobalOperator;
import com.example.springbootfinal.domain.serviceEntity.SubDuty;
import com.example.springbootfinal.domain.userEntity.BaseUser;
import com.example.springbootfinal.dto.Expert.RequestDto;
import com.example.springbootfinal.dto.Expert.SearchRequestDto;
import com.example.springbootfinal.exception.NotValidException;
import com.example.springbootfinal.service.FilterSpecificationFilterService;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.springbootfinal.domain.enumurations.Operation.*;

import org.springframework.data.jpa.domain.Specification;


public class BaseUserSpecification {

    public static Specification<BaseUser> isExpertOrCustomer() {
        return new Specification<BaseUser>() {
            @Override
            public Predicate toPredicate(Root<BaseUser> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                query.distinct(true);

                Predicate expertPredicate = criteriaBuilder.equal(root.get("DTYPE"), "Expert");
                Predicate customerPredicate = criteriaBuilder.equal(root.get("DTYPE"), "Customer");

                return criteriaBuilder.or(expertPredicate, customerPredicate);
            }
        };
    }

    public static Specification<BaseUser> hasFirstName(String firstName) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("firstName")), "%" + firstName.toLowerCase() + "%");
    }

    public static Specification<BaseUser> hasLastName(String lastName) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("lastName")), "%" + lastName.toLowerCase() + "%");
    }

    public static <T extends BaseUser> Specification<T> hasSubDuty(String subDutyName) {
        return (root, query, cb) -> {
            Join<T, SubDuty> subDutyJoin = root.join("subDuty");
            return cb.like(cb.lower(subDutyJoin.get("subServiceName")), "%" + subDutyName.toLowerCase() + "%");
        };
    }

    public static <T extends BaseUser> Specification<T> minRating(Integer stars) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("stars"), stars);
    }

    public static <T extends BaseUser> Specification<T> maxRating(Integer stars) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("stars"), stars);
    }

}

