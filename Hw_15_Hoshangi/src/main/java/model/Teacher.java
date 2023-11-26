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
    private String userName;
    private String password;
    private String phoneNumber;
    private Integer countOfUnit;
    @Enumerated(value = EnumType.STRING)
    RateOfTeacher rateOfTeacher;
    @OneToMany(mappedBy = "teacher")
    List<TermOfTeacher>list;
    @OneToMany(mappedBy = "lesson")
    private List<Lesson>lesson;




    public Double calculateSalary(Integer term){
        double salary=0;
        if (rateOfTeacher.equals(RateOfTeacher.doctor)){
            salary=5000000 + (countOfUnit * 1000000);
        }else if (rateOfTeacher.equals(RateOfTeacher.coTeacher)) {
            salary = countOfUnit * 1000000;
        }return salary;
    }
}
