package com.example.springbootfinal.dto.Expert;

import com.example.springbootfinal.domain.enumurations.GlobalOperator;
import com.example.springbootfinal.domain.enumurations.Operation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchRequestDto {
    String column;
    String value;
}
