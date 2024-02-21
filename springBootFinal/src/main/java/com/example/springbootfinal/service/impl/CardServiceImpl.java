package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.other.Card;
import com.example.springbootfinal.exception.DuplicateException;
import com.example.springbootfinal.repository.CardRepository;
import com.example.springbootfinal.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("unused")
public class CardServiceImpl implements CardService {
    CardRepository cardRepository;

    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public void saveCard(String cardNumber, String cvv2, String month, String password, String year) {
        boolean present = cardRepository.findByCardNumber(cardNumber).isPresent();
        if (present) {
            throw new DuplicateException("i have this card");
        }
        Card card = new Card();
        card.setCardNumber(cardNumber);
        card.setCvv2(cvv2);
        card.setMonth(month);
        card.setYear(year);
        card.setPassword(password);
        cardRepository.save(card);
    }
}
