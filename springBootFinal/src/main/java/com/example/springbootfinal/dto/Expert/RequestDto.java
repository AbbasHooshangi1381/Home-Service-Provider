package com.example.springbootfinal.dto.Expert;

import com.example.springbootfinal.domain.enumurations.GlobalOperator;
import com.example.springbootfinal.domain.enumurations.Operation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestDto {
    SearchRequestDto searchRequestDto;

}
