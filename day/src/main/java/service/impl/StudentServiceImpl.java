package service.impl;

import base.service.impl.BaseEntityServiceImpl;
import entity.Student;
import repository.StudentRepository;
import service.StudentService;

import java.util.Optional;

public class StudentServiceImpl extends BaseEntityServiceImpl<Integer, Student, StudentRepository> implements StudentService {
    public StudentServiceImpl(StudentRepository baseRepository) {
        super(baseRepository);
    }

    @Override
    public boolean existByUserName(String userName) {
        return baseRepository.existByUserName(userName);
    }

    @Override
    public Optional<Student> showUsernameAndPasswordForStudentNextSignup(String username) {
        return baseRepository.showUsernameAndPasswordForStudentNextSignup(username);
    }

    @Override
    public boolean existByNationalCode(String nationalCode) {
        return baseRepository.existByNationalCode(nationalCode);
    }

    @Override
    public Optional<Student> login(String username, String password) {
        return baseRepository.login(username, password);
    }
}
