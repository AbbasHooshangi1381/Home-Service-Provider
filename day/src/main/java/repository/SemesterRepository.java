package repository;

import base.repository.BaseEntityRepository;
import entity.Semester;
import entity.Student;

import java.util.Optional;

public interface SemesterRepository extends BaseEntityRepository<Integer , Semester> {



    Optional<Semester> findSemesterByStudentId(Student student);

    Integer maxRecordSemesterNumber(Student student);

}
