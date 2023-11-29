package repository.impl;

import base.repository.BaseRepositoryImpl;
import model.Lesson;
import model.SelectedLesson;
import model.Student;
import repository.StudentRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class StudentRepositoryImpl extends BaseRepositoryImpl<Student, Integer> implements StudentRepository {


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
        TypedQuery<Student> query =
                entityManager.createQuery("select s from  Student  s ", Student.class);
        return query.getResultList();
    }

    @Override
    public double getAverageMarksForStudent(Integer studentId) {
        Query query = entityManager.createQuery("SELECT AVG(s.grade) FROM SelectedLesson  s WHERE s.student.id = :studentId");
        query.setParameter("studentId",studentId);
        Double averageMarks = (Double) query.getSingleResult();
        return averageMarks != null ? averageMarks : 0.0;

    }

    @Override
    public void addCourseToStudent(Integer studentId, Lesson lesson) {
        Student student = entityManager.find(Student.class, studentId);
        if (student != null) {
            List<SelectedLesson> studentLesson = student.getSelectedLessonList();

            int totalUnits = studentLesson.stream()
                    .mapToInt(a -> a.getLesson().getUnitCountOfLesson())
                    .sum();

            int unitLimit = 0;
            double averageMarks = getAverageMarksForStudent(studentId);

            if (averageMarks >= 18) {
                unitLimit = 24;
            } else if (averageMarks >= 12) {
                unitLimit = 20;
            } else {
                unitLimit = 14;
            }

            if (studentLesson.stream()
                    .noneMatch(a -> a.getLesson().equals(lesson)) &&
                    (totalUnits + lesson.getUnitCountOfLesson()) <= unitLimit) {

                SelectedLesson selectedLesson = new SelectedLesson();
                selectedLesson.setLesson(lesson);
                selectedLesson.setStudent(student);

                entityManager.persist(selectedLesson);
            }
        }
    }

    @Override
    public Optional<Student> findById(Integer integer) {
        return super.findById(integer);
    }
}
