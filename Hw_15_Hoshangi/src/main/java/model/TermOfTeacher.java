package model;

import base.model.BaseEntity;
import enumuration.RateOfTeacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.swing.text.html.parser.DTDConstants.ID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "term_of_teacher")
@Entity
public class TermOfTeacher extends BaseEntity<Integer> {

    @Column(nullable = false)
    private Integer term;

    @Column(nullable = false)
    private Integer unit;

    @Column(nullable = false)
    private Double salary;

    @Enumerated(value = EnumType.STRING)
    RateOfTeacher rateOfTeacher;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;


    public double calculate(Integer term , RateOfTeacher rateOfTeacher, Integer ID){
        double salary=0;
        if (rateOfTeacher.equals(RateOfTeacher.doctor)){
            salary=5000000+(unit*1000000);

        }else if(rateOfTeacher.equals(RateOfTeacher.coTeacher)){
            salary=unit*1000000;
        }return salary;
    }


}
