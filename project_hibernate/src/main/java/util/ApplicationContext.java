package util;

import repository.CardRepository;
import repository.InstallmentRepository;
import repository.LoanRepository;
import repository.StudentRepository;
import repository.impl.CardRepositoryImpl;
import repository.impl.InstalmentRepositoryImpl;
import repository.impl.LoanRepositoryImpl;
import repository.impl.StudentRepositoryImpl;
import service.CardService;
import service.InstallmentsService;
import service.LoanService;
import service.StudentService;
import service.impl.CardServiceImpl;
import service.impl.InstallmentsServiceImpl;
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
    private static final CardService cardService;
    private static final InstallmentRepository installmentRepository;
    private static final InstallmentsService installmentService;


    static {
        entityManager = EntityManerFactoryProvider.getEntityManagerFactory().createEntityManager();
        studentRepository = new StudentRepositoryImpl(entityManager);
        studentService = new StudentServiceImpl(studentRepository);
        loanRepository = new LoanRepositoryImpl(entityManager);
        loanService = new LoanServiceImpl(loanRepository);
        cardRepository = new CardRepositoryImpl(entityManager);
        cardService = new CardServiceImpl(cardRepository);
        installmentRepository = new InstalmentRepositoryImpl(entityManager);
        installmentService = new InstallmentsServiceImpl(installmentRepository);
    }

    public static StudentService getStudentServiceImpl() {
        return studentService;
    }

    public static LoanService getLoanService() {
        return loanService;
    }

    public static CardService getCardService() {
        return cardService;
    }

    public static InstallmentsService getInstallmentService() {
        return installmentService;
    }
}
