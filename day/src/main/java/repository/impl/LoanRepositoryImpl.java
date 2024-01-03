package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import entity.Loan;
import entity.Semester;
import entity.Student;
import entity.enumuration.Degree;
import entity.enumuration.LoanType;
import repository.LoanRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDate;

public class LoanRepositoryImpl extends BaseEntityRepositoryImpl<Integer, Loan> implements LoanRepository {
    public LoanRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Loan> getEntityClass() {
        return Loan.class;
    }

    @Override
    public boolean existOneLoanPaymentPerSemester(Student student, int semesterNumber, Degree degree, LoanType loanType) {
        TypedQuery<Long> typedQuery = entityManager
                .createQuery("SELECT COUNT (l) FROM Loan l INNER JOIN  "
                        + " Semester s ON s.id=l.semester.id"
                        + " WHERE  l.student =:student"
                        + " AND s.semesterNumber=:semesterNumber"
                        + " AND s.degree =:degree" +
                        "  AND l.loanType=:loanType", Long.class)
                .setParameter("student", student)
                .setParameter("semesterNumber", semesterNumber)
                .setParameter("degree", degree)
                .setParameter("loanType", loanType);

        return typedQuery.getSingleResult() >= 1;
    }

    @Override
    public boolean exist(Student student, Degree degree) {
        TypedQuery<Long> loanTypedQuery = entityManager.createQuery("SELECT COUNT (l) FROM Loan l INNER JOIN Semester s ON l.semester.id =s.id WHERE " +
                        "s.student =: student AND s.degree =: degree AND l.loanType =:loanType ", Long.class)
                .setParameter("student", student)
                .setParameter("degree", degree)
                .setParameter("loanType", LoanType.Housing);
        return loanTypedQuery.getSingleResult() >= 1;

    }

    @Override
    public boolean existWifeTakeHousingLoan(Student student, Degree degree) {
        TypedQuery<Long> typedQuery = entityManager.createQuery("SELECT COUNT (l) from Loan l INNER JOIN Semester s ON l.semester.id=s.id WHERE " +
                        "s.student =: student AND s.degree =: degree AND l.loanType =: loanType ", Long.class)
                .setParameter("student", student)
                .setParameter("degree", degree)
                .setParameter("loanType", LoanType.Housing);
        return typedQuery.getSingleResult() >= 1;

    }

    @Override
    public LocalDate maxRecordEntriesDate(Student student) {
        Query student1 = entityManager.createQuery("SELECT MAX (s.entriesYear)FROM Semester s WHERE s.student =: student")
                .setParameter("student", student);
        return (LocalDate) student1.getSingleResult();
    }
}
