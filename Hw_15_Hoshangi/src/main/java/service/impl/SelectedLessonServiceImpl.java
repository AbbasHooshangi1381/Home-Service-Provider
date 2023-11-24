package service.impl;

import base.service.BaseServiceImpl;
import model.Lesson;
import model.SelectedLesson;
import repository.LessonRepository;
import repository.SelectedLessonRepository;
import service.LessonService;
import service.SelectedLessonService;

public class SelectedLessonServiceImpl extends BaseServiceImpl<SelectedLesson,Integer , SelectedLessonRepository>
        implements SelectedLessonService {

    SelectedLessonRepository selectedLessonRepository;

    public SelectedLessonServiceImpl(SelectedLessonRepository repository) {
        super(repository);
    }

    @Override
    public Double average(Integer term, Integer id) {
        return selectedLessonRepository.average(term, id);
    }

    @Override
    public Integer sumUnit(Integer term, Integer id) {
        return selectedLessonRepository.sumUnit(term, id);
    }

    @Override
    public void SelectedLessonOFStudent(SelectedLesson selectedLesson, Integer term, Integer id) {
        selectedLessonRepository.SelectedLessonOFStudent(selectedLesson, term, id);
    }

    @Override
    public void CanNotSelectLesson(String LessonName) {
        selectedLessonRepository.CanNotSelectLesson(LessonName);
    }
}
