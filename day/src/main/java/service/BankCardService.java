package service;

import base.service.BaseEntityService;
import entity.BankCard;
import entity.Student;

public interface BankCardService extends BaseEntityService<Integer, BankCard> {
    boolean existByNumberOfCard(String numberOfCard);

    boolean existBanCardByNumberCardAndCcvAndExpirationDate(Student student, String numberOfCard , String cvv2
            , String expirationDate);

    boolean hasBankCardForStudent(Student student);
}
