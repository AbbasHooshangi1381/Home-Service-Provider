package service.impl;

import base.service.BaseServiceImpl;
import lombok.NoArgsConstructor;
import model.Lesson;
import model.SelectedLesson;
import model.Student;
import org.hibernate.mapping.Set;
import repository.LessonRepository;
import repository.StudentRepository;
import repository.impl.StudentRepositoryImpl;
import service.LessonService;
import service.StudentService;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class StudentServiceImpl extends BaseServiceImpl<Student, Integer, StudentRepository> implements StudentService {

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

    @Override
    public void addCourseToStudent(Integer studentId, Lesson lesson) {
        studentRepository.addCourseToStudent(studentId, lesson);
    }

    @Override
    public Student login(String userName, String password) {
        return super.login(userName, password);
    }

    @Override
    public double getAverageMarksForStudent(Integer studentId) {
        return studentRepository.getAverageMarksForStudent(studentId);
    }

}
