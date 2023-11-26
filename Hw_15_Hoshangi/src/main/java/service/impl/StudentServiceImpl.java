package service.impl;

import base.service.BaseServiceImpl;
import model.Lesson;
import model.Student;
import repository.LessonRepository;
import repository.StudentRepository;
import repository.impl.StudentRepositoryImpl;
import service.LessonService;
import service.StudentService;

import java.util.List;
import java.util.Optional;

public class StudentServiceImpl extends BaseServiceImpl<Student,Integer , StudentRepository> implements StudentService {

    StudentRepositoryImpl studentRepository;


    public StudentServiceImpl(StudentRepository repository) {
        super(repository);
    }

    @Override
    public List<Student> loadAll() {
        return studentRepository.loadAll();
    }

    @Override
    public Optional<Student> findById(Integer integer) {
        return super.findById(integer);
    }
}
