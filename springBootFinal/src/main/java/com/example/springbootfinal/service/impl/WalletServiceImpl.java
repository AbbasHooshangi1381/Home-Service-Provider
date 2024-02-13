package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.enumurations.StatusOfOrder;
import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.other.Wallet;
import com.example.springbootfinal.domain.userEntity.Customer;
import com.example.springbootfinal.domain.userEntity.Expert;
import com.example.springbootfinal.exception.NotEnoughCreditException;
import com.example.springbootfinal.exception.NotFoundException;
import com.example.springbootfinal.repository.CustomerOrderRepository;
import com.example.springbootfinal.repository.ExpertRepository;
import com.example.springbootfinal.repository.WalletRepository;
import com.example.springbootfinal.service.WalletService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Service
@Transactional
public class WalletServiceImpl implements WalletService {

    @Autowired
    CustomerOrderRepository customerOrderRepository;
    @Autowired
    WalletRepository walletRepository;
    @Autowired
    ExpertRepository expertRepository;

    @Override
    public void payByCreditOfAccount(Integer customerOrderId, Integer expertId) {
        CustomerOrder customerOrder = customerOrderRepository.findById(customerOrderId).orElseThrow(() ->
                new NotFoundException(" i can not found this customer order"));
        Wallet wallet = walletRepository.findCreditAmountByCustomerOrderId(customerOrderId);
        if (wallet==null){
            throw new NotFoundException("i can not found this wallet");
        }
        Double creditAmount = wallet.getCreditAmount();
        Double price = customerOrder.getSubService().getPrice();
        Double seventyPercentPrice = price * 0.7;
        if (wallet == null) {
            throw new NotEnoughCreditException("you dont have enough credit");
        } else {
            Double newCredit = creditAmount - price;
            if (newCredit<0){
                throw new NotEnoughCreditException("you do not have enough credit to pay");
            }
            wallet.setCreditAmount(newCredit);
            customerOrder.setStatusOfOrder(StatusOfOrder.PAID);
            Wallet wallet1 = walletRepository.findWalletByExpertId(expertId).orElseThrow(() -> new NotFoundException(" i can not found this Expert id"));
            wallet1.setCreditAmount(seventyPercentPrice);
            walletRepository.save(wallet);
            walletRepository.save(wallet1);
            customerOrderRepository.save(customerOrder);
        }
    }
    @Override
    public void payByCard(Integer customerOrderId, Integer expertId) {
        CustomerOrder customerOrder = customerOrderRepository.findById(customerOrderId).orElseThrow(() ->
                new NotFoundException(" i can not found this customer order"));
        Double price = customerOrder.getSubService().getPrice();
        Double seventyPercentPrice = price * 0.7;
        Wallet wallet = walletRepository.findCreditAmountByCustomerOrderId(customerOrderId);
        if (wallet==null){
            throw new NotFoundException("i can not found this wallet");
        }
        Double creditAmount = wallet.getCreditAmount();
        Double newCredit = creditAmount - price;
        if (newCredit<0){
            throw new NotEnoughCreditException("you do not have enough credit to pay");
        }
        wallet.setCreditAmount(newCredit);
        customerOrder.setStatusOfOrder(StatusOfOrder.PAID);
        Wallet wallet1 = walletRepository.findWalletByExpertId(expertId).orElseThrow(() -> new NotFoundException(" i can not found this Expert id"));
        wallet1.setCreditAmount(seventyPercentPrice);
        walletRepository.save(wallet);
        walletRepository.save(wallet1);
        customerOrderRepository.save(customerOrder);
    }
}
