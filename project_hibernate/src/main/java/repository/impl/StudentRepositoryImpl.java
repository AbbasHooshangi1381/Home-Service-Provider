package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import entity.Student;
import repository.StudentRepository;

import javax.persistence.EntityManager;

public class StudentRepositoryImpl extends BaseEntityRepositoryImpl<Integer, Student> implements StudentRepository {
    public StudentRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Student> getEntityClass() {
        return Student.class;
    }


    @Override
    public Boolean canUseLoan(Integer id, Integer term) {
        entityManager.createQuery("from Student s where s.id=:id AND s.)
    }
}
