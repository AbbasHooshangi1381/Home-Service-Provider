package service.impl;

import base.service.impl.BaseEntityServiceImpl;
import entity.Semester;
import entity.Student;
import repository.SemesterRepository;

import java.util.Optional;

public class SemesterServiceImpl extends BaseEntityServiceImpl<Integer, Semester, SemesterRepository> implements service.SemesterService {
    public SemesterServiceImpl(SemesterRepository baseRepository) {
        super(baseRepository);
    }

    @Override
    public Optional<Semester> findSemesterByStudentId(Student student) {
        return baseRepository.findSemesterByStudentId(student);
    }

    @Override
    public Integer maxRecordSemesterNumber(Student student) {
        return baseRepository.maxRecordSemesterNumber(student);
    }
}
