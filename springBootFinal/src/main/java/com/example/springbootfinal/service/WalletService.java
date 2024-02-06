package com.example.springbootfinal.service;

import org.springframework.stereotype.Service;

@Service
public interface WalletService{
    void payByCreditOfAccount(Integer customerOrderId,Integer expertId);
    void payByCard(Integer customerOrderId);
}
