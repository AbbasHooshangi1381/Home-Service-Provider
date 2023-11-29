package service;

import base.service.BaseService;
import model.Lesson;
import model.SelectedLesson;

public interface SelectedLessonService extends BaseService<SelectedLesson, Integer> {
    Double average(Integer term,Integer id);

    Integer sumUnit(Integer term,Integer id);

    void SelectedLessonOFStudent(SelectedLesson selectedLesson, Integer term, Integer id);

    void CanNotSelectLesson(String LessonName);

    boolean lessonNotPassed(String lessonName);

    boolean lessonAlreadyChosen(String SelectedLessonName);

    SelectedLesson addGrade(Integer ID , Integer score);

}
