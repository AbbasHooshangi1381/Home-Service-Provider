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

    public Specification<T> getSearchSpecification(List<SearchRequestDto> searchRequestDto, GlobalOperator globalOperator) {
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
                    case IN -> {
                        String[] split = requestDto.getValue().split(",");
                        Predicate in = root.get(requestDto.getColumn()).in(Arrays.asList(split));
                        predicates.add(in);
                    }
                    case THE_BIGGEST -> {
                        Predicate greaterThan = criteriaBuilder.greaterThan(root.get(requestDto.getColumn()), requestDto.getValue());
                        predicates.add(greaterThan);
                    }
                    case THE_LEAST -> {
                        Predicate lessThan = criteriaBuilder.lessThan(root.get(requestDto.getColumn()), requestDto.getValue());
                        predicates.add(lessThan);
                    }
                 /*   case JOIN -> {
                        Predicate join = criteriaBuilder.equal(root.join(requestDto.getJoinTable()).get(requestDto.getColumn()), requestDto.getValue());
                        predicates.add(join);
                    }*/


                    default -> throw new NotValidException("Unexpected value");
                }

            }
            if (globalOperator.equals(GlobalOperator.AND)) {
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            } else {
                return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
            }

        };
    }
}

