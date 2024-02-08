package com.example.springbootfinal.dto.Expert;

import com.example.springbootfinal.domain.enumurations.GlobalOperator;
import com.example.springbootfinal.domain.enumurations.Operation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequestDto {
    String column;
    String value;
    Operation operation;

}
