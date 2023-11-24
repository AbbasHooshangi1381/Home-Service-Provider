package repository.impl;

import base.repository.BaseRepositoryImpl;
import model.Lesson;
import model.Student;
import repository.StudentRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class StudentRepositoryImpl extends BaseRepositoryImpl<Student , Integer> implements StudentRepository {


    public StudentRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Student> getEntityClass() {
        return Student.class;
    }

    @Override
    public Student saveOrUpdate(Student entity) {
        return super.saveOrUpdate(entity);
    }

    @Override
    public void deleteById(Integer integer) {
        super.deleteById(integer);
    }


    @Override
    public List<Student> loadAll() {
        TypedQuery<Student>query=
                entityManager.createQuery("select s from  Student  s ", Student.class);
        return query.getResultList();
    }


}
