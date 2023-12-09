package application;

import repository.StudentRepository;
import repository.impl.StudentRepositoryImpl;
import service.StudentService;
import service.impl.StudentServiceImpl;

import javax.persistence.EntityManager;

/*public class ApplicationContext {
    private final static EntityManager entityManager;
    private static final StudentRepository studentRepository;
    private static final StudentService studentService;

    static {
        entityManager = EntityManerFactoryProvider.getEntityManagerFactory().createEntityManager();
        studentRepository = new StudentRepositoryImpl(entityManager);
        studentService = new StudentServiceImpl(studentRepository);

    }

    public static StudentService getStudentServiceImpl() {
        return studentService;
    }

}*/
