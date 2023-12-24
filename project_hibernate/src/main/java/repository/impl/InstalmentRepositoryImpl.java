package repository.impl;
import base.repository.impl.BaseEntityRepositoryImpl;
import entity.Installment;
import entity.Student;
import entity.enumuration.LoanStatus;
import repository.InstallmentRepository;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
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
    public List<Installment> showInstallments(Integer id) {
        Query query = entityManager.createQuery("SELECT l.countOfLoan FROM Loan l WHERE l.student.id = :id");
        query.setParameter("id", id);

        return query.getResultList();
    }


    @Override
    public List<Installment> unpaidInstallments(Integer id) {
            return entityManager.createQuery("SELECT i FROM Installment i " +
                            "WHERE i.loan.student.id = :id " +
                            "AND i.loanStatus = :loanStatus", Installment.class)
                    .setParameter("loanStatus",LoanStatus.INCOMPLETE_PAID)
                    .setParameter("id", id)
                    .getResultList();
        }

    @Override
    public Boolean payInstallments(Integer payNumber, Integer studentId) {
        Query query = entityManager.createQuery("  select i from Installment i WHERE i.payNumber = :payNumber" +
                " and i.loan.student.id= :studentId");
        query.setParameter("payNumber", payNumber);
        query.setParameter("studentId", studentId);
        List resultList = query.getResultList();


        if (resultList != null) {
            Query query1 = entityManager.createQuery("update Installment i set i.loanStatus = :COMPLETE_PAID where " +
                    "i.loan.student.id= :studentId AND i.payNumber= :paynumber");
            query1.setParameter("COMPLETE_PAID", LoanStatus.COMPLETE_PAID);
            query1.setParameter("paynumber", payNumber);
            query1.setParameter("studentId", studentId);
            query1.executeUpdate();
            return true;
        } else {
            return false;
        }

    }

    @Override
    public List<Installment> paidInstallments(Integer studentId) {
        return entityManager.createQuery("  select i.timeOfDepositingInstallmentByUser" +
                        ",i.payNumber from Installment i WHERE" +
                        "  i.loanStatus = :loanStatus AND i.loan.student.id=:studentId", Installment.class)
                .setParameter("loanStatus", LoanStatus.COMPLETE_PAID)
                .setParameter("studentId", studentId)
                .getResultList();


    }

}
