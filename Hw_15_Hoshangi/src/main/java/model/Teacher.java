package model;

import base.model.BaseEntity;
import enumuration.LessonStatus;
import enumuration.RateOfTeacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@PrimaryKeyJoinColumn(name = "teacher_id")

public class Teacher extends BaseEntity<Integer> {

    private String firstname;
    private String lastName;
 //   private String userName;
//    private String password;
    private String phoneNumber;
    private Integer countOfUnit;

    @Enumerated(value = EnumType.STRING)
    RateOfTeacher rateOfTeacher;

    @OneToMany(mappedBy = "teacher")
    List<TermOfTeacher>list;

    @OneToMany(mappedBy = "teacher")
    private List<Lesson>lesson;


}
