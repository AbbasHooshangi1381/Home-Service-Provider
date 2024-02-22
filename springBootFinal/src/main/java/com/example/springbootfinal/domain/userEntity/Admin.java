package com.example.springbootfinal.domain.userEntity;

import com.example.springbootfinal.domain.enumurations.Role;
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

    @Override
    public String getUsername() {
        return super.getUsername();
    }


    public Admin(String firstName, String lastName, String email, String userName,
                 String password, LocalDate dateOfSigningIn, Boolean enabled, Role role) {
        super(firstName, lastName, email, userName, password, dateOfSigningIn,enabled,role);
    }

    @Override
    public boolean isEnabled() {
        return super.isEnabled();
    }
}
