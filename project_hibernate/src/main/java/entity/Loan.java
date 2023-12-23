package entity;

import base.domin.BaseEntity;
import entity.enumuration.HowToPay;
import entity.enumuration.LoanType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Loan extends BaseEntity<Integer> {


    Double countOfLoan;

    LocalDate dateOfStartLoan;

    @ManyToOne
    Student student;

    @OneToMany(mappedBy = "loan")
    List<Installment>installments;

    @Enumerated(value = EnumType.STRING)
    HowToPay howToPay;

    @OneToOne
    LoanCategory loanCategory;

}
