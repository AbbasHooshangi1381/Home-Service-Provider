package util;

import repository.*;
import repository.impl.*;
import service.*;
import service.impl.*;

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
    private static final WifeAndHomeRepository wifeAndHomeRepository;
    private static final WifeAndHomeService wifeAndHomeService;


    static {
        entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        studentRepository = new StudentRepositoryImpl(entityManager);
        studentService = new StudentServiceImpl(studentRepository);
        loanRepository = new LoanRepositoryImpl(entityManager);
        loanService = new LoanServiceImpl(loanRepository);
        cardRepository = new CardRepositoryImpl(entityManager);
        cardService = new CardServiceImpl(cardRepository);
        installmentRepository = new InstalmentRepositoryImpl(entityManager);
        installmentService = new InstallmentsServiceImpl(installmentRepository);
        wifeAndHomeRepository=new WifeAndHomeRepositoryImpl(entityManager);
        wifeAndHomeService=new WifeAndHomeServiceImpl(wifeAndHomeRepository);
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

    public static WifeAndHomeService getWifeAndHomeService() {
        return wifeAndHomeService;
    }
}
