package com.example.springbootfinal.domain.other;

import com.example.springbootfinal.baseDomain.BaseEntity;
import com.example.springbootfinal.domain.userEntity.Customer;
import com.example.springbootfinal.domain.userEntity.Expert;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Wallet extends BaseEntity<Integer> {

    Double creditAmount;

    public Wallet(Double creditAmount) {
        this.creditAmount = creditAmount;
    }

    @OneToOne(mappedBy = "wallet")
    Expert expert;

    @OneToOne(mappedBy = "wallet")
    Customer customer;
}
