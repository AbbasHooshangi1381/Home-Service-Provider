package com.example.springbootfinal.dto.Expert;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CriteriaSearchDto {
    String firstName;
    String lastName;
    String email;
    String specialistField;
    Integer stars;
}
