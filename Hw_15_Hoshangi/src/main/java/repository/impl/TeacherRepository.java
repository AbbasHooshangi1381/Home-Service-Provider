package repository.impl;

import base.repository.BaseRepositoryImpl;
import model.Student;
import model.Teacher;
import repository.StudentRepository;

import javax.persistence.EntityManager;

public class TeacherRepository extends BaseRepositoryImpl<Teacher, Integer> implements repository.TeacherRepository {

    public TeacherRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Teacher> getEntityClass() {
        return Teacher.class;
    }


    @Override
    public Teacher saveOrUpdate(Teacher entity) {
        return super.saveOrUpdate(entity);
    }

    @Override
    public void deleteById(Integer integer) {
        super.deleteById(integer);
    }
}
