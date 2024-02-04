package com.example.springbootfinal.dto.Admin;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class BaseResponseDto {
    Integer id;
    String firstName;
    String lastName;
}
