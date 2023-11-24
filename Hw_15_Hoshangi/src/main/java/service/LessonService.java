package service;

import base.service.BaseService;
import model.Lesson;

import java.util.List;

public interface LessonService extends BaseService<Lesson, Integer> {
    List<Lesson> loadAllLessons();

}
