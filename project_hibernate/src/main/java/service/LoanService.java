package service;

import base.service.BaseEntityService;
import entity.Loan;
import entity.Student;
import entity.enumuration.City;
import entity.enumuration.UniversityType;

import java.time.LocalDate;
import java.util.Date;

public interface LoanService extends BaseEntityService<Integer, Loan> {
    //---------------student of  ASSOCIATE_DEGREE, MASTERS----------------------------------

    void addEducationLoanToCard(Integer id);

    Boolean isLoanDateEligible(LocalDate lastLoanDate);

    //-----------------------------------------------------------------------------------------------------------//
    //-----------------------------------------------------------------------------------------------------------//
    //-----------------------------------------------------------------------------------------------------------//

    void addHousingLoanOfStudent(Integer id , City city);

    //-----------------------------------------------------------------------------------------------------------//
    //-----------------------------------------------------------------------------------------------------------//
    //-----------------------------------------------------------------------------------------------------------//

    void addPaymentOfUniversity(Integer id , UniversityType universityType);

    void graduatedStudent(Integer id);


}
