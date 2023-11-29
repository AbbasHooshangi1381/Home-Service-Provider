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

    @Column(nullable = false)
    private String lessonName;

    @Column(nullable = false)
    private Integer UnitCountOfLesson;


    @Enumerated(value = EnumType.STRING)
    LessonStatus lessonStatus;

    @ManyToOne
    @JoinColumn(name = "teacher")
    private Teacher teacher;

    @OneToMany(mappedBy="lesson")
    List<SelectedLesson> selectedLessonList;

}


