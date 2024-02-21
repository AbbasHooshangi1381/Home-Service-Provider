package com.example.springbootfinal.domain.userEntity;

import com.example.springbootfinal.domain.enumurations.Role;
import com.example.springbootfinal.domain.other.Card;
import com.example.springbootfinal.domain.other.Comments;
import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.other.Wallet;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@Entity

public class Customer extends BaseUser {

    @OneToMany(mappedBy = "customer")
    List<CustomerOrder> customerOrderList;

    @OneToOne
    Wallet wallet;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    List<Comments> commentsList;

    @ManyToOne
    @JsonIgnore
    Admin admin;


    public Customer(String firstName, String lastName, String email, String userName,
                    String password, LocalDate dateOfSigningIn, Wallet wallet, Boolean enabled, Role role) {
        super(firstName, lastName, email, userName, password, dateOfSigningIn,enabled,role);
        this.wallet = wallet;
    }



    @Override
    public boolean isEnabled() {
        return super.isEnabled();
    }
}
