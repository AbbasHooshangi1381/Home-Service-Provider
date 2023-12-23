package entity;

import base.domin.BaseEntity;
import entity.enumuration.HowToPay;
import entity.enumuration.LoanStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Installment extends BaseEntity<Integer> {

    Integer payNumber;

    Double amountOfEachInstallment;

    LocalDate timeOfPayInstallment;// لیست پرداخت شده ها را به وسیله این فیلد نشان میدهیم

    LocalDate timeOfDepositingInstallmentByUser;//لبست پرداخت نشده ها را در به وسیله این می بینیم + مبلغ و تاریخ سر رسید

    @ManyToOne
    Loan loan;

    @Enumerated(value = EnumType.STRING)
    LoanStatus loanStatus;


}
