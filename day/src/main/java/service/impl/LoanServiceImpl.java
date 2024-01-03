package service.impl;

import base.service.impl.BaseEntityServiceImpl;
import entity.Loan;
import entity.Student;
import entity.enumuration.Degree;
import entity.enumuration.LoanType;
import repository.LoanRepository;
import service.LoanService;

import java.time.LocalDate;

public class LoanServiceImpl extends BaseEntityServiceImpl<Integer, Loan, LoanRepository> implements LoanService {
    public LoanServiceImpl(LoanRepository baseRepository) {
        super(baseRepository);
    }

    @Override
    public boolean existOneLoanPaymentPerSemester(Student student, int semesterNumber, Degree degree, LoanType loanType) {
        return baseRepository.existOneLoanPaymentPerSemester(student, semesterNumber, degree, loanType);
    }

    @Override
    public boolean exist(Student student, Degree degree) {
        return baseRepository.exist(student, degree);
    }

    @Override
    public boolean existWifeTakeHousingLoan(Student student, Degree degree) {
        return baseRepository.existWifeTakeHousingLoan(student, degree);
    }

    @Override
    public LocalDate maxRecordEntriesDate(Student student) {
        return baseRepository.maxRecordEntriesDate(student);
    }
}
