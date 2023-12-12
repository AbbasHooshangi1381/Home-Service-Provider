package repository;

import base.domin.BaseEntity;
import base.repository.BaseEntityRepository;
import entity.Loan;

import java.util.Date;

public interface LoanRepository extends BaseEntityRepository<Integer, Loan> {

    //---------------student of  ASSOCIATE_DEGREE, MASTERS----------------------------------

    void addEducationLoanToCard(Integer id);

    Boolean isLoanDateEligible(Date lastLoanDate);

    //-----------------------------------------------------------------------------------------------------------//
    //-----------------------------------------------------------------------------------------------------------//
    //-----------------------------------------------------------------------------------------------------------//

    void addHousingLoanOfStudent(Integer id , String city);

    //-----------------------------------------------------------------------------------------------------------//
    //-----------------------------------------------------------------------------------------------------------//
    //-----------------------------------------------------------------------------------------------------------//

    void addPaymentOfUniversity(Integer id);

}
