package entity;

import base.domin.BaseEntity;
import entity.enumuration.LoanType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Loan extends BaseEntity<Integer> {


    @Enumerated(value = EnumType.STRING)
    LoanType loanType;
    LocalDate loanDate;
    Double amount;

    @ManyToOne
    Student student;

    @ManyToOne
    Semester semester;

    public Loan(Integer integer) {
        super(integer);

    }
}
