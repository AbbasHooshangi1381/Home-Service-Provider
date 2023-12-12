package entity;

import base.domin.BaseEntity;
import entity.enumuration.HowToPay;
import entity.enumuration.LoanStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Installment extends BaseEntity<Integer> {

    String timePaid;

    Double amountOfEachInstallment;

    LocalDate timeOfPayInstallment;

    LocalDate timeOfDepositingInstallmentByUser;

    @ManyToOne
    Loan loan;

    @Enumerated
    LoanStatus loanStatus;


}
