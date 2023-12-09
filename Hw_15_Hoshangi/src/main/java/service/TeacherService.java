package service;

import base.service.BaseService;
import model.Lesson;
import model.SelectedLesson;
import model.Teacher;

import java.util.Collection;
import java.util.Optional;

public interface TeacherService extends BaseService<Teacher, Integer> {
    @Override
    Optional<Teacher> findById(Integer integer);

    //double calculateSalary(Integer term);


    @Override
    Teacher saveOrUpdate(Teacher entity);

    @Override
    void deleteById(Integer integer);

    @Override
    Collection<Teacher> findAll();

    @Override
    Teacher login(String userName, String password);
}
