package entity;

import base.domin.BaseEntity;
import entity.enumuration.Degree;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Semester extends BaseEntity<Integer> {

    Integer semesterNumber;
    LocalDate entriesYear;

    @Enumerated(value=EnumType.STRING)
    Degree degree;

    @ManyToOne
    Student student;

    @ManyToOne
    University university;

    @ToString.Exclude
    @OneToMany(mappedBy = "semester")
    List<Loan> loan;

    public Semester(Integer semesterNumber, LocalDate entriesYear, Degree degree, Student student, University university) {
        this.semesterNumber = semesterNumber;
        this.entriesYear = entriesYear;
        this.degree = degree;
        this.student = student;
        this.university = university;
    }
}
