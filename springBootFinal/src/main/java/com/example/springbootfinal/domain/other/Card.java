package com.example.springbootfinal.domain.other;

import com.example.springbootfinal.baseDomain.BaseEntity;
import com.example.springbootfinal.domain.userEntity.Customer;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Card extends BaseEntity<Integer> {

    @Pattern(regexp = "\\d{3,4}", message = "CVV2 must be 3 or 4 digits")
    @NotNull
    String cvv2;

    @Size(min = 16, max = 16, message = "Card number must be exactly 16 digits")
    @NotNull
    String cardNumber;

    @NotNull
    String month;

    @Size(min = 2, max = 2, message = "Year must be 2 digits")
    @NotNull
    String year;
    @NotNull
    String password;

    public Card( String cvv2, String cardNumber, String month, String year, String password) {
        this.cvv2 = cvv2;
        this.cardNumber = cardNumber;
        this.month = month;
        this.year = year;
        this.password = password;

    }
}
