package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import entity.Installment;
import repository.InstallmentRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class InstalmentRepositoryImpl extends BaseEntityRepositoryImpl<Integer, Installment> implements InstallmentRepository {
    public InstalmentRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Installment> getEntityClass() {
        return Installment.class;
    }

    @Override
    public double installment(Integer id, Integer year) {
        Query query = entityManager.createQuery("select l.countOfLoan from Loan l where id=:id");
        query.setParameter("id", id);

        List<Integer> resultList = query.getResultList();
        if (!resultList.isEmpty()) {
            Integer amountOfLoan = resultList.get(0);
            double amountOfLoanWithProfit = amountOfLoan * 0.004;
            double CalculateAmountOfLoanPlusAmountOfProfit = amountOfLoanWithProfit + amountOfLoan;
            double InstallmentsOfYear1 = CalculateAmountOfLoanPlusAmountOfProfit / 31;
            return Math.pow(2,year-1)*InstallmentsOfYear1;
        }
        return 0.00;
    }
}
