package entity;

import base.domin.BaseEntity;
import entity.enumuration.City;
import entity.enumuration.LoanType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoanCategory extends BaseEntity<Integer> {

    Integer numberOfContract;

    Double AmountOfRemainingOfLoan;

    @OneToOne
    Loan loan;

    @Enumerated
    LoanType loanType;

    @Enumerated
    City city;

}
