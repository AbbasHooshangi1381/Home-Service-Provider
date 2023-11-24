package model;

import base.model.BaseEntity;
import enumuration.RateOfTeacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Table(name = "term_of_teacher")
@Entity
public class TermOfTeacher extends BaseEntity<Integer> {

    private Integer term;
    private Integer unit;
    private Double salary;
    @Enumerated(value = EnumType.STRING)
    RateOfTeacher rateOfTeacher;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    public TermOfTeacher(Integer integer, Integer term, Integer unit,
                         Double salary, RateOfTeacher rateOfTeacher, Teacher teacher) {
        super(integer);
        this.term = term;
        this.unit = unit;
        this.salary = calculate(term);
        this.rateOfTeacher = rateOfTeacher;
        this.teacher = teacher;
    }

    public Double calculate(Integer term){
        double salary=0;
        if (rateOfTeacher.equals(RateOfTeacher.doctor)){
            salary=5000000+(unit*1000000);

        }else if(rateOfTeacher.equals(RateOfTeacher.coTeacher)){
            salary=unit*1000000;
        }return salary;
    }


}
