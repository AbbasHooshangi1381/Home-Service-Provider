package com.example.springbootfinal.dto.Admin;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class BaseSaveDto {
    String firstName;
    String lastName;
    String email;
    String userName;


}
