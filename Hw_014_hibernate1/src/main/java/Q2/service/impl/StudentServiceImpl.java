package Q2.service.impl;

import Q2.entity.Student;
import Q2.repository.impl.StudentRepositoryImpl;
import Q2.service.PersonService;

import java.util.List;

public class StudentServiceImpl implements PersonService<Student> {

    StudentRepositoryImpl studentRepositoryImpl;

    public StudentServiceImpl(StudentRepositoryImpl studentRepositoryImpl) {
        this.studentRepositoryImpl = studentRepositoryImpl;
    }

    @Override
    public void save(Student student) {
        studentRepositoryImpl.save(student);
    }

    @Override
    public void update(Student student, Integer id) {
        studentRepositoryImpl.update(student,id);
    }

    @Override
    public Student delete(Integer id) {
        return studentRepositoryImpl.delete(id);
    }

    @Override
    public List<Student> loadAll() {
        return studentRepositoryImpl.loadAll();
    }

    @Override
    public Student find(Integer id) {
        return studentRepositoryImpl.find(id);
    }

    @Override
    public boolean contains(Integer id) {
        return studentRepositoryImpl.contains(id);
    }
}
