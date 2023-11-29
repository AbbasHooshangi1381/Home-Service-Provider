package model;

import base.model.BaseEntity;
import enumuration.LessonStatus;
import enumuration.RateOfTeacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@PrimaryKeyJoinColumn(name = "teacher_id")

public class Teacher extends BaseEntity<Integer> {

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastName;

    @Min(value = 8, message = "number should not be less than 8")
    @Max(value = 12, message = "number should not be greater than 12")
    @Column(nullable = false)
    private String phoneNumber;

    @Enumerated(value = EnumType.STRING)
    RateOfTeacher rateOfTeacher;

    @OneToMany(mappedBy = "teacher")
    List<TermOfTeacher>list;

    @OneToMany(mappedBy = "teacher")
    private List<Lesson>lesson;

}
