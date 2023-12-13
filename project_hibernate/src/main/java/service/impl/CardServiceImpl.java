package service.impl;

import base.service.impl.BaseEntityServiceImpl;
import entity.Card;
import entity.Loan;
import repository.CardRepository;
import repository.LoanRepository;
import service.CardService;
import service.LoanService;

public class CardServiceImpl  extends BaseEntityServiceImpl<Integer, Card, CardRepository>
        implements CardService {

    public CardServiceImpl(CardRepository baseRepository) {
        super(baseRepository);
    }
}
