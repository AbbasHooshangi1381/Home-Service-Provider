package Q2.apllication;

import Q2.config.SessionFactoryProvider;
import Q2.repository.impl.StudentRepositoryImpl;
import Q2.repository.impl.TeacherRepositoryImpl;
import Q2.service.impl.StudentServiceImpl;
import Q2.service.impl.TeacherServiceImpl;
import org.hibernate.Session;
import Q2.repository.PersonRepository;
import Q2.repository.impl.PersonRepositoryImpl;
import Q2.service.PersonService;
import Q2.service.impl.PersonServiceImpl;

public class ApplicationContext {

    private static final Session session;

    private static final PersonRepositoryImpl personRepositoryImpl;
    private static final PersonServiceImpl personServiceImpl;

    private static final StudentRepositoryImpl studentRepositoryImpl;
    private static final StudentServiceImpl studentServiceImpl;

    private static final TeacherRepositoryImpl teacherRepositoryImpl;
    private static final TeacherServiceImpl teacherServiceImpl;

    static {
        session = SessionFactoryProvider.getSessionFactory().openSession().getSession();

        personRepositoryImpl = new PersonRepositoryImpl(session);
        personServiceImpl = new PersonServiceImpl(personRepositoryImpl);

        studentRepositoryImpl=new StudentRepositoryImpl(session);
        studentServiceImpl=new StudentServiceImpl(studentRepositoryImpl);

        teacherRepositoryImpl=new TeacherRepositoryImpl(session);
        teacherServiceImpl=new TeacherServiceImpl(teacherRepositoryImpl);


    }

    public static PersonServiceImpl getPersonService() {
        return personServiceImpl;
    }

    public static StudentServiceImpl getStudentServiceImpl() {
        return studentServiceImpl;
    }

    public static TeacherServiceImpl getTeacherServiceImpl() {
        return teacherServiceImpl;
    }
}
