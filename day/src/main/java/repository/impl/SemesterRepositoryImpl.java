package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import entity.Semester;
import entity.Student;
import repository.SemesterRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Optional;

public class SemesterRepositoryImpl extends BaseEntityRepositoryImpl<Integer, Semester> implements SemesterRepository {
    public SemesterRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Semester> getEntityClass() {
        return Semester.class;
    }

    @Override
    public Optional<Semester> findSemesterByStudentId(Student student) {
        TypedQuery<Semester> semesterTypedQuery = entityManager.createQuery("SELECT s FROM Semester s WHERE s.student=:student AND " +
                        "s.id=:id ", Semester.class)
                .setParameter("student", student)
                .setParameter("id", maxRecordSemesterNumber(student));
        return Optional.ofNullable(semesterTypedQuery.getSingleResult());
    }

    @Override
    public Integer maxRecordSemesterNumber(Student student) {
        TypedQuery<Integer> query =
                entityManager.createQuery("SELECT MAX (s.id) FROM Semester s WHERE s.student=:student",
                                Integer.class)
                        .setParameter("student", student);
        return query.getSingleResult();
    }
}
