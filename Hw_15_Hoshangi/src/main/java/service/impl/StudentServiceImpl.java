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

public class StudentServiceImpl extends BaseServiceImpl<Student,Integer , StudentRepository> implements StudentService {

    StudentRepositoryImpl studentRepository;


    public StudentServiceImpl(StudentRepository repository) {
        super(repository);
    }

    @Override
    public List<Student> loadAll() {
        return studentRepository.loadAll();
    }


}
