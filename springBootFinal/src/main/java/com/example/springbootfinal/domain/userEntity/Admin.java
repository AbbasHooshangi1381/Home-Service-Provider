package com.example.springbootfinal.domain.userEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Admin extends BaseUser {

    @OneToMany(mappedBy = "admin")
    List<Customer> customerList;

    @OneToMany(mappedBy = "admin")
    List<Expert> expertList;

    public Admin(String firstName, String lastName, String email, String userName,
                 String password, LocalDate dateOfSigningIn) {
        super(firstName, lastName, email, userName, password, dateOfSigningIn);

    }
}
