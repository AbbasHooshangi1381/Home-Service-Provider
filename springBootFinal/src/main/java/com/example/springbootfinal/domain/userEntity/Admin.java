package com.example.springbootfinal.domain.userEntity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Admin extends BaseUser {

    @OneToMany(mappedBy = "admin")
   // @JoinColumn(name = "customer_id",referencedColumnName = "id")
    List<Customer> customerList;

    @OneToMany(mappedBy = "admin")
   // @JoinColumn(name = "expert_id",referencedColumnName = "id")
    List<Expert> expertList;


    public Admin(String firstName, String lastName, String email, String userName,
                 String password, LocalDate dateOfSigningIn) {
        super(firstName, lastName, email, userName, password, dateOfSigningIn);

    }


}
