package repository;

import base.repository.BaseRepository;
import model.Lesson;
import model.SelectedLesson;

public interface SelectedLessonRepository extends BaseRepository<SelectedLesson, Integer> {
    Double average(Integer term, Integer id);

    Integer sumUnit(Integer term, Integer id);

    void SelectedLessonOFStudent(SelectedLesson selectedLesson, Integer term, Integer id);

    Boolean CanNotSelectLesson(String LessonName);

    Lesson getLessonByName(String lessonName);
}
