package util;

import repository.*;
import repository.impl.*;
import service.*;
import service.impl.*;

import javax.persistence.EntityManager;

public class ApplicationContext {

    private static final EntityManager entityManager;
    private static final BankCardRepository bankCardRepository;
    private static final BankCardService bankCardService;
    private static final InstallmentRepository installmentRepository;
    private static final InstallmentService installmentService;
    private static final LoanRepository loanRepository;
    private static final LoanService loanService;
    private static final MarriedRepository marriedRepository;
    private static final MarriedService marriedService;
    private static final SemesterRepository semesterRepository;
    private static final SemesterService semesterService;
    private static final StudentRepository studentRepository;
    private static final StudentService studentService;
    private static final UniversityRepository universityRepository;
    private static final UniversityService universityService;

    static {
        entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        bankCardRepository = new BankCardRepositoryImpl(entityManager);
        bankCardService = new BankCardServiceImpl(bankCardRepository);
        installmentRepository = new InstallmentRepositoryImpl(entityManager);
        installmentService = new InstallmentServiceImpl(installmentRepository);
        loanRepository = new LoanRepositoryImpl(entityManager);
        loanService = new LoanServiceImpl(loanRepository);
        marriedRepository = new MarriedRepositoryImpl(entityManager);
        marriedService = new MarriedServiceImpl(marriedRepository);
        semesterRepository = new SemesterRepositoryImpl(entityManager);
        semesterService = new SemesterServiceImpl(semesterRepository);
        studentRepository = new StudentRepositoryImpl(entityManager);
        studentService = new StudentServiceImpl(studentRepository);
        universityRepository = new UniversityRepositoryImpl(entityManager);
        universityService = new UniversityServiceImpl(universityRepository);

    }

    public static BankCardService getBankCardService() {
        return bankCardService;
    }

    public static InstallmentService getInstallmentService() {
        return installmentService;
    }

    public static LoanService getLoanService() {
        return loanService;
    }

    public static MarriedService getMarriedService() {
        return marriedService;
    }

    public static SemesterService getSemesterService() {
        return semesterService;
    }

    public static StudentService getStudentService() {
        return studentService;
    }

    public static UniversityService getUniversityService() {
        return universityService;
    }


}
