package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import entity.Student;
import repository.StudentRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

public class StudentRepositoryImpl extends BaseEntityRepositoryImpl<Integer, Student> implements StudentRepository {
    public StudentRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Student> getEntityClass() {
        return Student.class;
    }

    @Override
    public boolean existByUserName(String userName) {
        final TypedQuery<Long> typedQuery = entityManager.createQuery("SELECT COUNT (s) FROM Student " +
                        "s WHERE s.username=:username", Long.class)
                .setParameter("username", userName);
        return typedQuery.getSingleResult() >= 1;
    }

    @Override
    public Optional<Student> showUsernameAndPasswordForStudentNextSignup(String username) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery =criteriaBuilder.createQuery(Student.class);
        Root<Student> studentRoot = criteriaQuery.from(Student.class);
        criteriaQuery.multiselect(studentRoot.get("username"),studentRoot.get("password"));
        CriteriaQuery<Student> select =criteriaQuery.select(studentRoot)
                .where(criteriaBuilder.equal(studentRoot.get("username"),username));
        Student result = entityManager.createQuery(select)
                .getSingleResult();
        return Optional.ofNullable(result);
    }

    @Override
    public boolean existByNationalCode(String nationalCode) {
         TypedQuery<Long> typedQuery = entityManager.createQuery("SELECT COUNT (s) FROM Student s WHERE s.nationalCode=:nationalCode", Long.class)
                .setParameter("nationalCode", nationalCode);
         return typedQuery.getSingleResult()>=0;
    }
}
