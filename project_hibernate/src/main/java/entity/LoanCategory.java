package entity;

import base.domin.BaseEntity;
import entity.enumuration.City;
import entity.enumuration.LoanType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoanCategory extends BaseEntity<Integer> {

    Integer numberOfContract;

    Integer countOfRemainingOfLoanInstallment;

    @Enumerated(value = EnumType.STRING)
    LoanType loanType;

    @Enumerated(value = EnumType.STRING)
    City city;

}
