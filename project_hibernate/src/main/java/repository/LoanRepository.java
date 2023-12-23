package repository;

import base.repository.BaseEntityRepository;
import entity.Loan;
import entity.Student;
import entity.enumuration.City;
import entity.enumuration.UniversityType;

import java.time.LocalDate;
import java.util.Optional;

public interface LoanRepository extends BaseEntityRepository<Integer, Loan> {

    //---------------student of  ASSOCIATE_DEGREE, MASTERS----------------------------------

  //  void addEducationLoanToCard(Integer id);

 //   boolean isLoanDateEligible(LocalDate lastLoanDate);

    //-----------------------------------------------------------------------------------------------------------//
    //-----------------------------------------------------------------------------------------------------------//
    //-----------------------------------------------------------------------------------------------------------//

  //  void addHousingLoanOfStudent(Integer id , City city);

    //-----------------------------------------------------------------------------------------------------------//
    //-----------------------------------------------------------------------------------------------------------//
    //-----------------------------------------------------------------------------------------------------------//

  //  void addPaymentOfUniversity(Integer id , UniversityType universityType);

    LocalDate graduatedStudentForMenu(Integer id);

}
