package com.example.springbootfinal.dto.Expert;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class ExpertSaveDto {
    String firstName;
    String lastName;
    String email;
    String userName;
    LocalDate timeOfSignIn;
    String filePath;
}
