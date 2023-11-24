package service.impl;

import base.service.BaseServiceImpl;
import model.Lesson;
import model.Teacher;
import repository.LessonRepository;
import repository.TeacherRepository;
import repository.impl.LessonRepositoryImpl;
import service.LessonService;
import service.TeacherService;

import java.util.List;

public class LessonServiceImpl extends BaseServiceImpl<Lesson,Integer , LessonRepository> implements LessonService {

    LessonRepositoryImpl lessonRepository;

    public LessonServiceImpl(LessonRepository repository) {
        super(repository);
    }


    @Override
    public List<Lesson> loadAllLessons() {
        return lessonRepository.loadAllLessons();
    }
}
