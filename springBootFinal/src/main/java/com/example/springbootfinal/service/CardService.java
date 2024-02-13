package com.example.springbootfinal.service;

import com.example.springbootfinal.domain.other.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardService {

    void saveCard(String cardNumber, String cvv2, String month, String password, String year, Integer customerId);
}
