package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import entity.Installment;
import entity.enumuration.LoanStatus;
import repository.InstallmentRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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
    public void showInstallments(Integer id) {
        Query query = entityManager.createQuery("select l.countOfLoan from Loan l where l.id=:id");
        query.setParameter("id", id);

        List<Integer> resultList = query.getResultList();
        if (!resultList.isEmpty()) {
            Integer amountOfLoan = resultList.get(0);
            double amountOfLoanWithProfit = amountOfLoan * 0.004;
            double calculateAmountOfLoanPlusAmountOfProfit = amountOfLoanWithProfit + amountOfLoan;
            double installmentsOfYear1 = calculateAmountOfLoanPlusAmountOfProfit / 31;

            for (int i = 1; i <= 5; i++) {
                double installmentAmount = installmentsOfYear1 * Math.pow(2, i - 1);
                System.out.println("Installment amount for year " + i + ": " + installmentAmount);
            }
        } else {
            System.out.println("No loan found with the provided ID");
        }
    }

    @Override
    public void graduatedStudent(Integer id) {
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
                    System.out.println("You have " + remainingYears + " years remaining until graduation.");
                    System.out.println("You cannot request a loan at the moment.");
                } else {
                    System.out.println("You can access to the repayment page.");
                    showRepaymentOptions();
                }
            } else {
                System.out.println("You can not see the repayment menu!!");
            }
        } else {
            System.out.println("Invalid student ID.");
        }
    }

    @Override
    public List<Installment> unpaidInstallments(Integer id) {
        Query query = entityManager.createQuery("SELECT i FROM Installment i WHERE i.id = :id AND" +
                " i.loanStatus = :loanStatus");
        query.setParameter("id", id);
        query.setParameter("loanStatus", LoanStatus.INCOMPLETE_PAID);

        List<Installment> unpaidInstallmentsList = query.getResultList();
        return unpaidInstallmentsList;
    }

}
