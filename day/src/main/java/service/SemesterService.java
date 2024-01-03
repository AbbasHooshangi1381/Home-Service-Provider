package service;

import base.service.BaseEntityService;
import entity.Semester;
import entity.Student;

import java.util.Optional;

public interface SemesterService extends BaseEntityService<Integer, Semester> {

    Optional<Semester> findSemesterByStudentId(Student student);

    Integer maxRecordSemesterNumber(Student student);
}
