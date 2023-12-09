package application;

import menu.Menu;
import repository.*;
import repository.impl.*;
import service.*;
import service.impl.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;

public class ApplicationContext {


    EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("default");
    EntityManager entityManager = emf.createEntityManager();

    private static final EntityManager entityManager1;


    private static final EmployerRepository EDUCATION_EMPLOYEE_REPOSITORY;
    private static final EmployerService EDUCATION_EMPLOYEE_SERVICE;
    private static final StudentRepository STUDENT_REPOSITORY;
    private static final StudentService STUDENT_SERVICE;
    private static final TeacherRepository TEACHER_REPOSITORY;
    private static final TeacherService TEACHER_SERVICE;
    private static final LessonRepository LESSON_REPOSITORY;
    private static final LessonService LESSON_SERVICE;
    private static final SelectedLessonRepository LESSON_GRADES_REPOSITORY;
    private static final SelectedLessonService LESSON_GRADE_SERVICE;



    static {
        EDUCATION_EMPLOYEE_REPOSITORY = new EmployerRepositoryImpl(entityManager1) {
        };
        EDUCATION_EMPLOYEE_SERVICE = new EmployerServiceImpl(EDUCATION_EMPLOYEE_REPOSITORY);

        STUDENT_REPOSITORY = new StudentRepositoryImpl(entityManager1);
        STUDENT_SERVICE = new StudentServiceImpl(STUDENT_REPOSITORY);

        TEACHER_REPOSITORY = new TeacherRepositoryimpl(entityManager1);
        TEACHER_SERVICE = new TeacherServiceImpl(TEACHER_REPOSITORY);

        LESSON_REPOSITORY = new LessonRepositoryImpl(entityManager1);
        LESSON_SERVICE = new LessonServiceImpl(LESSON_REPOSITORY);

        LESSON_GRADES_REPOSITORY = new SelectedLessonRepositoryImpl(entityManager1);
        LESSON_GRADE_SERVICE = new SelectedLessonServiceImpl(LESSON_GRADES_REPOSITORY);


    }

    public static EmployerServiceImpl getEducationEmployeeService() {
        return (EmployerServiceImpl) EDUCATION_EMPLOYEE_SERVICE;
    }

    public static StudentService getStudentService() {
        return STUDENT_SERVICE;
    }

    public static TeacherService getTeacherService() {
        return TEACHER_SERVICE;
    }

    public static LessonService getLessonService(){
        return LESSON_SERVICE;
    }

    public static SelectedLessonServiceImpl getLessonGradeService(){ return (SelectedLessonServiceImpl) LESSON_GRADE_SERVICE;}


}
