package service.impl;

import base.service.impl.BaseEntityServiceImpl;
import entity.Student;
import repository.StudentRepository;
import service.StudentService;

public class StudentServiceImpl extends BaseEntityServiceImpl<Integer,Student, StudentRepository>
        implements StudentService {
    public StudentServiceImpl(StudentRepository baseRepository) {
        super(baseRepository);
    }

    @Override
    public Boolean canUseLoan(Integer id, Integer term) {
        return null;
    }
}
