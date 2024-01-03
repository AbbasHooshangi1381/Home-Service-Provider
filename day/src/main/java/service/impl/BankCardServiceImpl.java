package service.impl;

import base.service.impl.BaseEntityServiceImpl;
import entity.BankCard;
import entity.Student;
import repository.BankCardRepository;
import service.BankCardService;

public class BankCardServiceImpl extends BaseEntityServiceImpl<Integer, BankCard, BankCardRepository> implements BankCardService {
    public BankCardServiceImpl(BankCardRepository baseRepository) {
        super(baseRepository);
    }

    @Override
    public boolean existByNumberOfCard(String numberOfCard) {
            return baseRepository.existByNumberOfCard(numberOfCard);
    }

    @Override
    public boolean existBanCardByNumberCardAndCcvAndExpirationDate(Student student, String numberOfCard, String cvv2, String expirationDate) {
        return baseRepository.existBanCardByNumberCardAndCcvAndExpirationDate(student,numberOfCard,cvv2,expirationDate);
    }

    @Override
    public boolean hasBankCardForStudent(Student student) {
        return baseRepository.hasBankCardForStudent(student);
    }
}
