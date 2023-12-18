package repository;

import base.domin.BaseEntity;
import base.repository.BaseEntityRepository;
import entity.Loan;
import entity.enumuration.City;
import entity.enumuration.UniversityType;

import java.time.LocalDate;
import java.util.Date;

public interface LoanRepository extends BaseEntityRepository<Integer, Loan> {

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

    Boolean graduatedStudentForMenu(Integer id);

}
