package service.impl;

import base.service.BaseServiceImpl;
import enumuration.RateOfTeacher;
import model.Teacher;
import model.User;
import repository.TeacherRepository;
import repository.UserRepository;
import service.TeacherService;
import service.UserService;

import java.util.Locale;
import java.util.Optional;
import java.util.Scanner;

public class TeacherServiceImpl extends BaseServiceImpl<Teacher,Integer , TeacherRepository> implements TeacherService {
    Teacher teacher;
    public TeacherServiceImpl(TeacherRepository repository) {
        super(repository);
    }


    @Override
    public Optional<Teacher> findById(Integer integer) {
        return super.findById(integer);
    }

    @Override
    public Double calculateSalary(Integer term) {
        return teacher.calculateSalary(term);
    }
}

