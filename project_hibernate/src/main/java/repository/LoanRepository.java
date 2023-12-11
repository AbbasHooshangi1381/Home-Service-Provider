package repository;

import base.domin.BaseEntity;
import base.repository.BaseEntityRepository;
import entity.Loan;

import java.util.Date;

public interface LoanRepository extends BaseEntityRepository<Integer, Loan> {

    //---------------student of  ASSOCIATE_DEGREE, MASTERS----------------------------------

    void addEducationLoanToCard(Integer id);

    Boolean isLoanDateEligible(Date lastLoanDate);

    void addEducationLoanToCardCollumn2(Integer id);

    void addEducationLoanToCardCollumn3(Integer id);

    //-----------------------------------------------------------------------------------------------------------//
    //-----------------------------------------------------------------------------------------------------------//
    //-----------------------------------------------------------------------------------------------------------//

    void addHousingLoanOfStudentCollumn1(Integer id , String city);

    //-----------------------------------------------------------------------------------------------------------//
    //-----------------------------------------------------------------------------------------------------------//
    //-----------------------------------------------------------------------------------------------------------//

    void addPaymentOfUniversity(Integer id);

    void addPaymentOfUniversity2(Integer id);

    void addPaymentOfUniversity3(Integer id);
}
