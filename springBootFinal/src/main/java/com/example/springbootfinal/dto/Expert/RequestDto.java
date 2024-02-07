package com.example.springbootfinal.dto.Expert;

import com.example.springbootfinal.domain.enumurations.GlobalOperator;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequestDto {

    private List<SearchRequestDto> searchRequestDto;

    private GlobalOperator globalOperator;

}
