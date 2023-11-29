package repository.impl;

import base.repository.BaseRepositoryImpl;
import model.Lesson;
import model.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class TeacherRepositoryimpl extends BaseRepositoryImpl<Teacher, Integer> implements repository.TeacherRepository {

    Teacher teacher;

    public TeacherRepositoryimpl(EntityManager entityManager) {
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

    @Override
    public Optional<Teacher> findById(Integer integer) {
        return super.findById(integer);
    }

 /*   @Override
    public Lesson addGrade(Integer ID , Integer score) {
        Lesson lesson=entityManager.find(Lesson.class,ID);
        if (lesson != null) {
            lesson.setGrade(score);
        } else {
            System.out.println(" you added grade in past");
        }
        return lesson;

    }*/


    @Override
    public List<Teacher> loadAll() {
            TypedQuery<Teacher> query=
                    entityManager.createQuery("select s from  Teacher  s ", Teacher.class);
            return query.getResultList();
        }

   /* @Override
    public double calculateSalary(Integer term) {
        double salary=0;
        Query query = entityManager.createQuery("SELECT t FROM Teacher t WHERE t.firstname= :firstname and t.rateOfTeacher =:rateOfTeacher");
        query.setParameter("rateOfTeacher","doctor");
        Object singleResult = query.getSingleResult();
        return (Double) singleResult;
    }*/
}
