package com.example.springbootfinal.dto.card;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CardRequestDto {

    @Pattern(regexp = "^\\d{16}$", message = "Please enter a 16-digit number")
    private String cardNumber;
    @Pattern(regexp = "^\\d{3}$", message = "Please enter 3 number")
    private String cvv2;

    private String month;

    private String year;
    @Pattern(regexp = "^\\d{4}$", message = "Please enter 4 number")
    private String password;


}
