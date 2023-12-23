package repository.impl;
import base.repository.impl.BaseEntityRepositoryImpl;
import entity.Loan;
import repository.LoanRepository;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;

public class LoanRepositoryImpl extends BaseEntityRepositoryImpl<Integer, Loan> implements LoanRepository {
    public LoanRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }


    @Override
    public Class<Loan> getEntityClass() {
        return Loan.class;
    }


    @Override
    public LocalDate graduatedStudentForMenu(Integer id) {
        Query query = entityManager.createQuery("SELECT s.enterYear" +
                "  FROM Student s  WHERE s.id = :id");
        query.setParameter("id", id);
        return (LocalDate) query.getSingleResult();
    }

}

