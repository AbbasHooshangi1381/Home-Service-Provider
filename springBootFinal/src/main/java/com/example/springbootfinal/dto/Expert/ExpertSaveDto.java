package com.example.springbootfinal.dto.Expert;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class ExpertSaveDto {

    String firstName;
    String lastName;
    @Email(message = "Email should be valid")
    String email;
    String userName;
    String filePath;
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,}$",message = "your password is not valid")
    String password;
}
