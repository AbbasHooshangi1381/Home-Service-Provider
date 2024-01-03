package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import entity.BankCard;
import entity.Student;
import repository.BankCardRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class BankCardRepositoryImpl extends BaseEntityRepositoryImpl<Integer, BankCard> implements BankCardRepository {
    public BankCardRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<BankCard> getEntityClass() {
        return BankCard.class;
    }

    @Override
    public boolean existByNumberOfCard(String numberOfCard) {
        TypedQuery<Long> typedQuery = entityManager.createQuery("select count (b) from BankCard b" +
                        " where b.numberOfCard =: numberOfCard", Long.class)
                .setParameter("numberOfCard", numberOfCard);
        return typedQuery.getSingleResult() > 0;
    }

    @Override
    public boolean existBanCardByNumberCardAndCcvAndExpirationDate(Student student, String numberOfCard, String cvv2, String expirationDate) {
        TypedQuery<Long> typedQuery = entityManager.createQuery("select count (b) from BankCard b " +
                        "where b.numberOfCard =: numberOfCard AND b.cvv2 =:cvv2 and b.expirationDate =: expirationDate and " +
                        "b.student =: student", Long.class)
                .setParameter("numberOfCard", numberOfCard)
                .setParameter("cvv2", cvv2)
                .setParameter("expirationDate", expirationDate)
                .setParameter("student", student);

        return typedQuery.getSingleResult() >= 1;
    }

    @Override
    public boolean hasBankCardForStudent(Student student) {
         TypedQuery<Long> typedQuery = entityManager.createQuery("select count (b) from BankCard b where b.student =: student", Long.class)
                .setParameter("student", student);

         return typedQuery.getSingleResult()>=1;
    }
}

