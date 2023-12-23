package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import entity.Card;
import entity.Student;
import entity.enumuration.City;
import entity.enumuration.SectionOfStudy;
import repository.StudentRepository;

import javax.persistence.EntityManager;
import java.util.Calendar;
import java.util.Date;

import static entity.enumuration.City.*;


public class StudentRepositoryImpl extends BaseEntityRepositoryImpl<Integer, Student> implements StudentRepository {
    public StudentRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Student> getEntityClass() {
        return Student.class;
    }
}



