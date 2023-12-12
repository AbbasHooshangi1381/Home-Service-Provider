package util;

import repository.CardRepository;
import repository.LoanRepository;
import repository.StudentRepository;
import repository.impl.CardRepositoryImpl;
import repository.impl.LoanRepositoryImpl;
import repository.impl.StudentRepositoryImpl;
import service.LoanService;
import service.StudentService;
import service.impl.LoanServiceImpl;
import service.impl.StudentServiceImpl;

import javax.persistence.EntityManager;

public class ApplicationContext {
    private final static EntityManager entityManager;
    private static final StudentRepository studentRepository;
    private static final StudentService studentService;
    private static final LoanService loanService;
    private static final LoanRepository loanRepository;
    private static final CardRepository cardRepository;
    //private static final Car cardRepository;


    static {
        entityManager = EntityManerFactoryProvider.getEntityManagerFactory().createEntityManager();
        studentRepository = new StudentRepositoryImpl(entityManager);
        studentService = new StudentServiceImpl(studentRepository);
        loanRepository=new LoanRepositoryImpl(entityManager);
        loanService=new LoanServiceImpl(loanRepository);

        cardRepository=new CardRepositoryImpl(entityManager);
    }
    public static StudentService getStudentServiceImpl() {
        return studentService;
    }

    public static LoanService getLoanService() {
        return loanService;
    }
}
