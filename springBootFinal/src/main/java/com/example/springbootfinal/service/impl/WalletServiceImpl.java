package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.enumurations.StatusOfOrder;
import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.other.Wallet;
import com.example.springbootfinal.domain.userEntity.Customer;
import com.example.springbootfinal.domain.userEntity.Expert;
import com.example.springbootfinal.exception.DuplicateException;
import com.example.springbootfinal.exception.NotEnoughCreditException;
import com.example.springbootfinal.exception.NotFoundException;
import com.example.springbootfinal.repository.CustomerOrderRepository;
import com.example.springbootfinal.repository.CustomerRepository;
import com.example.springbootfinal.repository.ExpertRepository;
import com.example.springbootfinal.repository.WalletRepository;
import com.example.springbootfinal.service.WalletService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class WalletServiceImpl implements WalletService {

    CustomerOrderRepository customerOrderRepository;
    WalletRepository walletRepository;
    ExpertRepository expertRepository;
    CustomerRepository customerRepository;


    @Override
    public void payByCreditOfAccount(Integer customerOrderId, Integer expertId) {
        CustomerOrder customerOrder = customerOrderRepository.findById(customerOrderId).orElseThrow(() ->
                new NotFoundException(" i can not found this customer order"));
        Wallet wallet = walletRepository.findCreditAmountByCustomerOrderId(customerOrderId);
        if (wallet == null) {
            throw new NotFoundException("i can not found this wallet");
        }
        Double creditAmount = wallet.getCreditAmount();
        Double price = customerOrder.getSuggestionList().get(0).getSuggestionPrice();
        Double seventyPercentPrice = price * 0.7;
        if (creditAmount < price) {
            throw new NotEnoughCreditException("you dont have enough credit");
        } else {
            double newCredit = creditAmount - price;
            if (newCredit < 0) {
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
        if (customerOrder.getStatusOfOrder().equals(StatusOfOrder.PAID)) {
            throw new DuplicateException("you paid in past !");
        }
        Double price = customerOrder.getSubService().getPrice();
        Double seventyPercentPrice = price * 0.7;
        Wallet wallet = walletRepository.findCreditAmountByCustomerOrderId(customerOrderId);
        if (wallet == null) {
            throw new NotFoundException("i can not found this wallet");
        }
        Double creditAmount = wallet.getCreditAmount();
        double newCredit = creditAmount - price;
        if (newCredit < 0) {
            throw new NotEnoughCreditException("you do not have enough credit to pay");
        }
        wallet.setCreditAmount(newCredit);
        customerOrder.setStatusOfOrder(StatusOfOrder.PAID);
        Wallet wallet1 = walletRepository.findWalletByExpertId(expertId).orElseThrow(() -> new NotFoundException(" i can not found this Expert id"));
        wallet1.setCreditAmount(seventyPercentPrice);
        walletRepository.saveAndFlush(wallet);
        walletRepository.saveAndFlush(wallet1);
        customerOrderRepository.saveAndFlush(customerOrder);
    }

    @Override
    public Double findCreditOfWalletByCustomerId(String userName) {
         Customer customer = customerRepository.findByUserName(userName).orElseThrow(() -> new NotFoundException("i can not found this user"));
        Double creditOfWalletByCustomerId = walletRepository.findCreditOfWalletByCustomerId(customer.getId());
        if (creditOfWalletByCustomerId == null) {
            throw new NotFoundException(" i can not found this customer");
        }
        return creditOfWalletByCustomerId;
    }

    @Override
    public Double findCreditOfWalletByExpertId(String userName) {
        Expert expert = expertRepository.findByUserName(userName).orElseThrow(() -> new NotFoundException("i can not found this user"));
        Double creditOfWalletByExpertId = walletRepository.findCreditOfWalletByExpertId(expert.getId());
        if (creditOfWalletByExpertId == null) {
            throw new NotFoundException(" i can not found this customer");
        }
        return creditOfWalletByExpertId;
    }
}
