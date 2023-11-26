package repository.impl;

import base.repository.BaseRepositoryImpl;
import enumuration.LessonStatus;
import model.Lesson;
import model.SelectedLesson;
import model.Student;
import repository.SelectedLessonRepository;
import repository.StudentRepository;
import service.SelectedLessonService;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.time.LocalDate;
import java.util.List;

import static org.hibernate.cfg.AvailableSettings.PASS;

public class SelectedLessonRepositoryImpl extends BaseRepositoryImpl<SelectedLesson, Integer> implements SelectedLessonRepository {

    SelectedLesson selectedLesson;

    public SelectedLessonRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<SelectedLesson> getEntityClass() {
        return SelectedLesson.class;
    }

    @Override
    public Double average(Integer term, Integer id) {

        Query query = entityManager.createQuery
                ("select AVG (s.grade) FROM SelectedLesson s where s.id=:id and s.term=:term");
        query.setParameter("id", id);
        query.setParameter("term", term);
        return (Double) query.getSingleResult();
    }

    @Override
    public Integer sumUnit(Integer term, Integer id) {
        Query query = entityManager.createQuery
                ("select SUM (s.countOfUnit) FROM SelectedLesson s where s.id=:id and s.term=:term");
        query.setParameter("id", id);
        query.setParameter("term", term);
        return (Integer) query.getSingleResult();
    }

    @Override
    public void SelectedLessonOFStudent(SelectedLesson selectedLesson, Integer term, Integer id) {
        if (average(term, id) > 18) {
            while (sumUnit(term, id) <= 24) {
                entityManager.persist(selectedLesson);
            }
        }
        entityManager.persist(selectedLesson);
    }

    @Override
    public Boolean CanNotSelectLesson(String lessonName) {
        Lesson selectedLesson = getLessonByName(lessonName);
        if (selectedLesson != null && selectedLesson.getLessonStatus() == LessonStatus.PASS) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Lesson getLessonByName(String lessonName) {
        Query query = entityManager.createQuery("SELECT l FROM Lesson l WHERE l.lessonName = :lessonName");
        query.setParameter("lessonName", lessonName);
        return (Lesson) query.getSingleResult();
    }

    public boolean lessonNotPassed(String lessonName) {
        Query query = entityManager.createQuery("SELECT l FROM Lesson l WHERE " +
                "l.lessonName = :lessonName AND l.lessonStatus = :lessonStatus");
        query.setParameter("lessonName", lessonName);
        query.setParameter("lessonStatus", "PASS"); // Assuming "PASS" is the correct value
        List<Lesson> lessons = query.getResultList();

        if (!lessons.isEmpty()) {
                System.out.println("This lesson has already passed.");
                return false;
            } else {
                System.out.println("This lesson is upcoming.");
                return true;
        }
    }

    @Override
    public boolean lessonAlreadyChosen(String lessonName) {
        Query query = entityManager.createQuery("SELECT l FROM Lesson l WHERE l.lessonName = :lessonName");
        query.setParameter("lessonName", lessonName);
        List<Lesson> lessons = query.getResultList();

        if (!lessons.isEmpty()) {
            System.out.println("The lesson has already been chosen.");
            return true;
        } else {
            System.out.println("The lesson has not been chosen before.");
            return false;
        }
    }
}
