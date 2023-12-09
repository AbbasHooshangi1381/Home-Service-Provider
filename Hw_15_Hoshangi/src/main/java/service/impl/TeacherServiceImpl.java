package service.impl;

import base.service.BaseServiceImpl;
import lombok.NoArgsConstructor;
import model.Lesson;
import model.SelectedLesson;
import model.Teacher;
import repository.TeacherRepository;
import repository.impl.TeacherRepositoryimpl;
import service.TeacherService;

import java.util.Collection;
import java.util.Optional;

public class TeacherServiceImpl extends BaseServiceImpl<Teacher,Integer , TeacherRepository> implements TeacherService {


    public TeacherServiceImpl(TeacherRepository repository) {
        super(repository);
    }

    @Override
    public Teacher saveOrUpdate(Teacher entity) {
        return repository.saveOrUpdate(entity);
    }

    @Override
    public Optional<Teacher> findById(Integer integer) {
        return repository.findById(integer);
    }


    @Override
    public Teacher login(String userName, String password) {
        return repository.login(userName, password);
    }

    @Override
    public void deleteById(Integer integer) {
        repository.deleteById(integer);
    }

    @Override
    public Collection<Teacher> findAll() {
        return repository.findAll();
    }
}

