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

@Service
public class FilterSpecificationFilterServiceImpl<T> implements FilterSpecificationFilterService<T> {

    public  Specification<T> getSearchSpecification(List<SearchRequestDto> searchRequestDto, GlobalOperator globalOperator) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            for (SearchRequestDto requestDto : searchRequestDto) {
                switch (requestDto.getOperation()) {
                    case LIKE -> {
                        Predicate like = criteriaBuilder.like(root.get(requestDto.getColumn()), "%" + requestDto.getValue() + "%");
                        predicates.add(like);
                    }
                    case EQUAL -> {
                        Predicate equal = criteriaBuilder.equal(root.get(requestDto.getColumn()), requestDto.getValue());
                        predicates.add(equal);
                    }
                    case THE_BIGGEST -> {
                        Predicate greaterThanOrEqualTo = criteriaBuilder.greaterThanOrEqualTo(root.get(requestDto.getColumn()), requestDto.getValue());
                        predicates.add(greaterThanOrEqualTo);
                    }
                    case THE_LEAST -> {
                        Predicate lessThanOrEqualTo = criteriaBuilder.lessThanOrEqualTo(root.get(requestDto.getColumn()), requestDto.getValue());
                        predicates.add(lessThanOrEqualTo);
                    }
                    default -> throw new NotValidException("Unexpected value");
                }
            };
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

