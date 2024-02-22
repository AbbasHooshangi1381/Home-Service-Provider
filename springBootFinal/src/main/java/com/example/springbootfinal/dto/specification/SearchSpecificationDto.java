package com.example.springbootfinal.dto.specification;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class SearchSpecificationDto {
    String column;
    String value;
    Operation operation;
    String joinTable;


    public enum Operation{
        EQUAL,LIKE,IN,GREATER_THAN,LESS_THAN,BETWEEN,JOIN,
        DOUBLE_JOIN,JoinBySubDutyName,BetweenDateRegister,
        CUSTOMER_ORDER_COUNT,EXPERT_ORDER_COUNT
        }
}
