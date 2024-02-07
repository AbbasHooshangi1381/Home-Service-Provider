package com.example.springbootfinal.service;

import com.example.springbootfinal.domain.enumurations.GlobalOperator;
import com.example.springbootfinal.dto.Expert.SearchRequestDto;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface FilterSpecificationFilterService<T> {

    Specification<T> getSearchSpecification(List<SearchRequestDto> searchRequestDto, GlobalOperator globalOperator);
}