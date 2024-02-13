package com.example.springbootfinal.domain.other;

import com.example.springbootfinal.baseDomain.BaseEntity;
import com.example.springbootfinal.domain.userEntity.Customer;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Card extends BaseEntity<Integer> {
    @Min(3)
    @Max(5)
    @NotNull
    String cvv2;
    @Min(16)
    @Max(16)
    @NotNull
    String cardNumber;
    @NotNull
    String month;
    @NotNull
    String year;

    @NotNull
    String password;

    @ManyToOne
    Customer customer;

    public Card( String cvv2, String cardNumber, String month, String year, String password, Customer customer) {
        this.cvv2 = cvv2;
        this.cardNumber = cardNumber;
        this.month = month;
        this.year = year;
        this.password = password;
        this.customer = customer;
    }
}
