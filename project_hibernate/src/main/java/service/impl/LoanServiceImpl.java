package service.impl;

import base.service.impl.BaseEntityServiceImpl;
import entity.Loan;
import entity.Student;
import entity.enumuration.City;
import entity.enumuration.UniversityType;
import repository.LoanRepository;
import repository.StudentRepository;
import service.LoanService;
import service.StudentService;

import java.util.Date;

public class LoanServiceImpl extends BaseEntityServiceImpl<Integer, Loan, LoanRepository>
        implements LoanService {
    public LoanServiceImpl(LoanRepository baseRepository) {
        super(baseRepository);
    }

    @Override
    public void addEducationLoanToCard(Integer id) {
        baseRepository.addEducationLoanToCard(id);
    }

    @Override
    public Boolean isLoanDateEligible(Date lastLoanDate) {
        return baseRepository.isLoanDateEligible(lastLoanDate);
    }


    @Override
    public void addHousingLoanOfStudent(Integer id, City city) {
        baseRepository.addHousingLoanOfStudent(id, city);
    }

    @Override
    public void addPaymentOfUniversity(Integer id, UniversityType universityType) {
        baseRepository.addPaymentOfUniversity(id, universityType);
    }


}
