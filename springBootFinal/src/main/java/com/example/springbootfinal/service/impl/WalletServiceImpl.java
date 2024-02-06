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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    CustomerOrderRepository customerOrderRepository;
    @Autowired
    WalletRepository walletRepository;
    @Autowired
    ExpertRepository expertRepository;


    @Override
    public void payByCreditOfAccount(Integer customerOrderId, Integer expertId) {
        Wallet wallet = walletRepository.findCreditAmountByCustomerOrderId(customerOrderId);
        Double creditAmount = wallet.getCreditAmount();

        CustomerOrder customerOrder = customerOrderRepository.findById(customerOrderId).orElseThrow(() ->
                new NotFoundException(" i can not found this customer order"));

         Expert expert = expertRepository.findById(expertId).orElseThrow(() -> new NotFoundException(" i can not found this Expert id"));

        Double price = customerOrder.getSubService().getPrice();

        if (wallet != null) {
            Double newCredit = creditAmount - price;
            wallet.setCreditAmount(newCredit);
            customerOrder.setStatusOfOrder(StatusOfOrder.PAID);
        //    expert.setWallet();
            walletRepository.save(wallet);
            customerOrderRepository.save(customerOrder);

        } else {

            throw new NotEnoughCreditException("you dont have enough credit");

        }


    }

    @Override
    public void payByCard(Integer customerOrderId) {
        CustomerOrder customerOrder = customerOrderRepository.findById(customerOrderId).orElseThrow(() ->
                new NotFoundException(" i can not found this customer order"));

        customerOrder.setStatusOfOrder(StatusOfOrder.PAID);
        customerOrderRepository.save(customerOrder);

    }
}
