package model;

import base.model.BaseEntity;
import enumuration.LessonStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
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

    @Pattern(regexp = "^\\\\d+$")
    @Column(nullable = false)
    private Integer UnitCountOfLesson;



    @Enumerated(value = EnumType.STRING)
    LessonStatus lessonStatus;


    @ManyToOne
    @JoinColumn(name = "teacher")
    private Teacher teacher;

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    List<SelectedLesson> selectedLessonList;

    public Lesson(Integer integer, String userName, String password, String lessonName, Integer unitCountOfLesson, LessonStatus lessonStatus, Teacher teacher) {
        super(integer, userName, password);
        this.lessonName = lessonName;
        UnitCountOfLesson = unitCountOfLesson;
        this.lessonStatus = lessonStatus;
        this.teacher = teacher;
    }
}


