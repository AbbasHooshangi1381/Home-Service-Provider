package model;

import base.model.BaseEntity;
import enumuration.LessonStatus;
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
@Table(name = "selected_lesson")
@Entity
public class SelectedLesson extends BaseEntity<Integer> {


    private Integer countOfUnit;
    private Integer term;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

}
