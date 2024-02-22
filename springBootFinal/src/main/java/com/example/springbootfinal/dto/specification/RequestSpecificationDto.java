package com.example.springbootfinal.dto.specification;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequestSpecificationDto {
    private List<SearchSpecificationDto> searchSpecificationDto;
    private GlobalOperator globalOperator;

    public enum GlobalOperator{
        AND,OR
    }
}
