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
    public Boolean payInstallments(Integer payNumber, Integer studentId) {

        try {
            beginTransaction();
            List<Integer> installmentIds = entityManager.createQuery("SELECT i.id FROM Installment i WHERE i.payNumber = :payNumber AND i.loan.student.id = :studentId", Integer.class)
                    .setParameter("payNumber", payNumber)
                    .setParameter("studentId", studentId)
                    .getResultList();

            if (installmentIds != null && !installmentIds.isEmpty()) {
                entityManager.createQuery("UPDATE Installment i SET " +
                                "i.loanStatus = :completePaid WHERE i.id IN :installmentIds")
                        .setParameter("completePaid", LoanStatus.COMPLETE_PAID)
                        .setParameter("installmentIds", installmentIds)
                        .executeUpdate();

                commitTransaction();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
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
    public List<Object[]> paidInstallments(Integer studentId) {
        return entityManager.createQuery("select i.timeOfDepositingInstallmentByUser, i.payNumber from Installment i " +
                        "WHERE i.loanStatus = :loanStatus AND i.loan.student.id = :studentId", Object[].class)
                .setParameter("loanStatus", LoanStatus.COMPLETE_PAID)
                .setParameter("studentId", studentId)
                .getResultList();
    }

}
