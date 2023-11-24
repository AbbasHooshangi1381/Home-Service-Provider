package service;

import base.service.BaseService;
import model.Lesson;
import model.Student;

import java.util.List;

public interface StudentService extends BaseService<Student, Integer> {

    List<Student> loadAll();

}
