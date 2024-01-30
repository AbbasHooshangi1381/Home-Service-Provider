package com.example.springbootfinal.domain.userEntity;

import com.example.springbootfinal.baseDomain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@MappedSuperclass
@Getter
@Setter
@ToString
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class BaseUser extends BaseEntity<Integer> {
    String firstName;
    String lastName;
    @Column(unique = true)
    String email;
    String userName;
    String password;
    LocalDate dateOfSigningIn;

    public BaseUser( String firstName,
                    String lastName, String email, String userName, String password, LocalDate dateOfSigningIn) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        if (!isValidPassword(password)) {
            System.out.println("Invalid password format!");
        } else {
            this.password = password;
        }
        this.dateOfSigningIn = dateOfSigningIn;
    }

    private boolean isValidPassword(String password) {
        String pattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=])[A-Za-z\\d@#$%^&+=]{8,}$";
        return password.matches(pattern);
    }
}
