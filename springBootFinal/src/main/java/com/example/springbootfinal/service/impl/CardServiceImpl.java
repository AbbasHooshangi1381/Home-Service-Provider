package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.other.Card;
import com.example.springbootfinal.domain.userEntity.Customer;
import com.example.springbootfinal.exception.DuplicateException;
import com.example.springbootfinal.exception.NotFoundException;
import com.example.springbootfinal.repository.CardRepository;
import com.example.springbootfinal.repository.CustomerRepository;
import com.example.springbootfinal.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    CardRepository cardRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public void saveCard(String cardNumber, String cvv2, String month, String password, String year, Integer customerId) {
        boolean present = cardRepository.findByCardNumber(cardNumber).isPresent();
        if (present) {
            throw new DuplicateException("i have this card");
        }
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new NotFoundException(" i can not found this customer"));
        Card card = new Card();
        card.setCardNumber(cardNumber);
        card.setCvv2(cvv2);
        card.setMonth(month);
        card.setYear(year);
        card.setPassword(password);
        card.setCustomer(customer);
        cardRepository.save(card);
    }
}
