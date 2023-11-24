package repository;

import base.repository.BaseRepository;
import model.Employer;
import model.Lesson;

import java.util.List;

public interface LessonRepository extends BaseRepository<Lesson, Integer> {

    List<Lesson> loadAllLessons();


}
