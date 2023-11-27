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

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "lesson")
@Entity
public class Lesson extends BaseEntity<Integer> {

    private String lessonName;

    private Integer UnitCount;

    private String field;
    @Size(min = 0, max = 20, message = "your number is out of range")
    private Integer grade;

    @Enumerated(value = EnumType.STRING)
    LessonStatus lessonStatus;

    @ManyToOne
    @JoinColumn(name = "teacher")
    private Teacher teacher;

    @OneToMany(mappedBy="lesson")
    List<SelectedLesson> selectedLessonList;

}
