package repository;

import base.repository.BaseEntityRepository;
import entity.BankCard;
import entity.Student;

public interface BankCardRepository extends BaseEntityRepository<Integer, BankCard> {

    boolean existByNumberOfCard(String numberOfCard);

    boolean existBanCardByNumberCardAndCcvAndExpirationDate(Student student, String numberOfCard , String cvv2
            , String expirationDate);

    boolean hasBankCardForStudent(Student student);

}
