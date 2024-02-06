package com.example.springbootfinal.dto.customerOrder;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class PaymentDto {

    Integer cardNumber;
    Integer cvv2;
    Integer month;
    Integer year;
}
