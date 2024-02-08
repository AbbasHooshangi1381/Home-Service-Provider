package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.enumurations.GlobalOperator;
import com.example.springbootfinal.dto.Expert.RequestDto;
import com.example.springbootfinal.dto.Expert.SearchRequestDto;
import com.example.springbootfinal.exception.NotValidException;
import com.example.springbootfinal.service.FilterSpecificationFilterService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.springbootfinal.domain.enumurations.Operation.*;

@Service
public class FilterSpecificationFilterServiceImpl<T> implements FilterSpecificationFilterService<T> {


    @Override
    public Specification<T> getSearchSpecification(SearchRequestDto searchRequestDto) {
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get(searchRequestDto.getColumn()),searchRequestDto.getValue());
            }
        };
    }
}

