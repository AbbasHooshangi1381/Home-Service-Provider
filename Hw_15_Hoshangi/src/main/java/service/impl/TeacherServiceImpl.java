package service.impl;

import base.service.BaseServiceImpl;
import model.Lesson;
import model.SelectedLesson;
import model.Teacher;
import repository.TeacherRepository;
import repository.impl.TeacherRepositoryimpl;
import service.TeacherService;

import java.util.Optional;

public class TeacherServiceImpl extends BaseServiceImpl<Teacher,Integer , TeacherRepository> implements TeacherService {
    Teacher teacher;
    TeacherRepositoryimpl teacherRepositoryimpl;
    public TeacherServiceImpl(TeacherRepository repository) {
        super(repository);
    }


    @Override
    public Optional<Teacher> findById(Integer integer) {
        return super.findById(integer);
    }

  /*  @Override
    public Double calculateSalary(Integer term) {
        return teacher.calculateSalary(term);
    }*/

  /*  @Override
    public SelectedLesson addGrade(Integer ID, Integer score) {
        return teacherRepositoryimpl.addGrade(ID,score);
    }*/
}

