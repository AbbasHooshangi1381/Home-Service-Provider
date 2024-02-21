package com.example.springbootfinal.dto.customer;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class StartedDto {

    LocalDateTime localDateTime;
    Integer orderId;

}
