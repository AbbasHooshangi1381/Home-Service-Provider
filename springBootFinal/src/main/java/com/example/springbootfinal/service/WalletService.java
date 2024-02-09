package com.example.springbootfinal.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public interface WalletService{
    void payByCreditOfAccount(Integer customerOrderId,Integer expertId);
    void payByCard(Integer customerOrderId, Integer expertId);
}
