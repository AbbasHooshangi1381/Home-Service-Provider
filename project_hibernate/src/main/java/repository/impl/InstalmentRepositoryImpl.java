package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import entity.Installment;
import entity.enumuration.LoanStatus;
import repository.InstallmentRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static menu.Menu.showRepaymentOptions;

public class InstalmentRepositoryImpl extends BaseEntityRepositoryImpl<Integer, Installment> implements InstallmentRepository {
    public InstalmentRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Installment> getEntityClass() {
        return Installment.class;
    }


    @Override
    public List<Installment> showInstallments(Integer id) {
        Query query = entityManager.createQuery("SELECT l.countOfLoan FROM Loan l WHERE l.student.id = :id");
        query.setParameter("id", id);

        List<Integer> resultList = query.getResultList();
        List<Installment> installments = new ArrayList<>();

        if (!resultList.isEmpty()) {
            Integer amountOfLoan = resultList.get(0);
            double amountOfLoanWithProfit = amountOfLoan * 0.04;
            int installmentCounter = 1;

            for (int i = 1; i <= 5; i++) {
                System.out.println("Year " + i + ":");
                for (int j = 1; j <= 12; j++) {
                    double installmentAmount = amountOfLoanWithProfit * Math.pow(2, i - 1) / 12;
                    System.out.println("Month " + j + ": " + installmentAmount);
                    Installment installment = new Installment();
                    installment.setAmountOfEachInstallment(installmentAmount);
                    installment.setTimeOfPayInstallment(LocalDate.of(i, j, 1));
                    installment.setLoanStatus(LoanStatus.INCOMPLETE_PAID);
                    installment.setPayNumber(installmentCounter);

                    entityManager.persist(installment);
                    installments.add(installment);

                    installmentCounter++;
                }
            }
        } else {
            System.out.println("No loan found with the provided ID");
        }

        return installments;
    }

    @Override
    public String graduatedStudent(Integer id) {
        Query query = entityManager.createQuery("SELECT s.enterYear FROM Student s WHERE s.id = :id");
        query.setParameter("id", id);

        Object enterYear = query.getSingleResult();

        if (enterYear != null) {
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            int graduated = 0;

            int yearsOfGraduation = currentYear - Integer.parseInt(enterYear.toString());

            if (yearsOfGraduation >= 4 && yearsOfGraduation < 6) {
                graduated = Integer.parseInt(enterYear.toString()) + 4;
            } else if (yearsOfGraduation >= 2 && yearsOfGraduation < 4) {
                graduated = Integer.parseInt(enterYear.toString()) + 2;
            } else if (yearsOfGraduation >= 6 && yearsOfGraduation < 8) {
                graduated = Integer.parseInt(enterYear.toString()) + 6;
            } else if (yearsOfGraduation >= 8 && yearsOfGraduation < 10) {
                graduated = Integer.parseInt(enterYear.toString()) + 8;
            } else if (yearsOfGraduation >= 10 && yearsOfGraduation < 11) {
                graduated = Integer.parseInt(enterYear.toString()) + 10;
            }

            if (graduated != 0) {
                int remainingYears = graduated - currentYear;

                if (remainingYears > 0) {
                    String message = "You have " + remainingYears + " years remaining until graduation. You cannot request a loan at the moment.";
                    return message;
                } else {
                    showRepaymentOptions();
                    String message = "You can access the repayment page.";
                    return message;
                }
            } else {
                String message = "You cannot see the repayment menu!!";
                return message;
            }
        } else {
            String message = "Invalid student ID.";
            return message;
        }
    }

    @Override
    public List<Installment> unpaidInstallments(Integer payNumber, Integer studentId) {
        Query query = entityManager.createQuery(" FROM Installment i WHERE i.payNumber = :payNumber" +
                " AND i.loanStatus = :loanStatus AND i.loan.student.id=:studentId");
        query.setParameter("payNumber", payNumber);
        query.setParameter("loanStatus", LoanStatus.INCOMPLETE_PAID);
        query.setParameter("studentId", studentId);

        return query.getResultList();

    }

    @Override
    public Boolean payInstallments(Integer payNumber, Integer studentId) {
        Query query = entityManager.createQuery(" FROM Installment i WHERE i.payNumber = :payNumber");
        query.setParameter("payNumber", payNumber);

        List resultList = query.getResultList();

        if (resultList != null) {
            Query query1 = entityManager.createQuery("update Installment i set i.loanStatus =:paid where " +
                    "i.loan.student.id=:studentId AND i.payNumber=:payNumber");
            query1.setParameter("paid", LoanStatus.COMPLETE_PAID);
            query1.setParameter("payNumber", payNumber);
            query1.setParameter("studentId", studentId);
            query1.executeUpdate();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Installment> paidInstallments(Integer payNumber, Integer studentId) {
        Query query = entityManager.createQuery(" FROM Installment i WHERE i.payNumber = :payNumber" +
                " AND i.loanStatus = :loanStatus AND i.loan.student.id=:studentId");
        query.setParameter("payNumber", payNumber);
        query.setParameter("loanStatus", LoanStatus.COMPLETE_PAID);
        query.setParameter("studentId", studentId);

        return query.getResultList();


    }

}
