package repository;

import base.repository.BaseEntityRepository;
import entity.Loan;
import entity.Semester;
import entity.Student;
import entity.enumuration.Degree;
import entity.enumuration.LoanType;

import java.time.LocalDate;

public interface LoanRepository extends BaseEntityRepository<Integer, Loan> {
    boolean existOneLoanPaymentPerSemester(Student student, int semesterNumber, Degree degree, LoanType loanType);

    boolean exist(Student student , Degree degree);

    boolean existWifeTakeHousingLoan(Student student, Degree degree);

    LocalDate maxRecordEntriesDate(Student student);

}
