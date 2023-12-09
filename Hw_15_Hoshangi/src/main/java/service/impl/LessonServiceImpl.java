package service.impl;

import base.service.BaseServiceImpl;
import lombok.NoArgsConstructor;
import model.Lesson;
import repository.LessonRepository;
import repository.SelectedLessonRepository;
import repository.impl.LessonRepositoryImpl;
import service.LessonService;

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
