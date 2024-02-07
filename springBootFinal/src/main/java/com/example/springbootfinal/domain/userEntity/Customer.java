package com.example.springbootfinal.domain.userEntity;

import com.example.springbootfinal.domain.other.Comments;
import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.other.Wallet;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

public class Customer extends BaseUser {

    @OneToMany(mappedBy = "customer")
    List<CustomerOrder> customerOrderList;

    @OneToOne
    Wallet wallet;

    @OneToMany(mappedBy = "customer")
    List<Comments> commentsList;

    @ManyToOne
  //  @Column(name = "id")
    Admin admin;

    public Customer(String firstName, String lastName, String email, String userName,
                    String password, LocalDate dateOfSigningIn, Wallet wallet) {
        super(firstName, lastName, email, userName, password, dateOfSigningIn);
        this.wallet = wallet;
    }
}
