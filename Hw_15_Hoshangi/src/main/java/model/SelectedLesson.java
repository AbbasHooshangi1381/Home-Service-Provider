package model;

import base.model.BaseEntity;
import enumuration.LessonStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class SelectedLesson extends BaseEntity<Integer> {

    @Column(nullable = false)
    private String SelectedLessonName;

    @Column(nullable = false)
    private Integer SelectedLessonUnit;

    @Column(nullable = false)
    private Integer countOfUnit;

    @Enumerated
    LessonStatus SelectedLessonStatus;

    @Column(nullable = false)
    @Size(min = 0, max = 15, message = "your number is out of range")
    private Integer term;

    @Size(min = 0, max = 20, message = "your number is out of range")
    private Integer grade;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;


}
