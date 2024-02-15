package com.example.springbootfinal.domain.userEntity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity

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
