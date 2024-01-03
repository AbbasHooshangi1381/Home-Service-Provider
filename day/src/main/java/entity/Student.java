package entity;

import base.domin.BaseEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student extends BaseEntity<Integer>{
    String firstname;
    String lastname;
    String fathername;
    String mothername;
    String nationalIdNumber;
    String nationalCode;
    LocalDate birthday;
    String studentNumber;
    Boolean married;
    Boolean dormitory;
    String username;
    String password;

    @OneToMany(mappedBy = "student")
    @ToString.Exclude
    List<Loan>loanList;

    @OneToMany(mappedBy = "student")
    @ToString.Exclude
    List<BankCard> bankCardList;

    @OneToMany(mappedBy = "student")
    @ToString.Exclude
    List<Semester>semesterList;

    @OneToMany(mappedBy = "student")
    @ToString.Exclude
    List<Installment>installmentList;

    @ManyToOne
    University university;


    public Student(Integer id) {
        super(id);
    }

    public Student(Integer integer, String firstname) {
        super(integer);
        this.firstname = firstname;
    }

    public Student(String firstname) {
        this.firstname = firstname;
    }



}
