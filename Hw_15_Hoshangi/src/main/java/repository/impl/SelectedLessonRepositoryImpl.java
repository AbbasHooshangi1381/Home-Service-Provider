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
}
