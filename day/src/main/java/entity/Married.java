package entity;

import base.domin.BaseEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Married extends BaseEntity<Integer> {
    String address;
    String rentalNumber;
    @ManyToOne
    Student student;

    @ManyToOne
    Student wife;

    @OneToOne
    Loan loan;

    public Married( Loan loan) {
        this.loan = loan;
    }
}
