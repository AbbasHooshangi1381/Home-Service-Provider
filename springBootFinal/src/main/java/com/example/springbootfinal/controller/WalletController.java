package com.example.springbootfinal.controller;

import com.example.springbootfinal.dto.Expert.SendOfferRequestDto;
import com.example.springbootfinal.repository.WalletRepository;
import com.example.springbootfinal.service.WalletService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
@CrossOrigin("*")
@RestController
@RequestMapping("/wallet")
public class WalletController {

    private ModelMapper modelMapper;
    private WalletRepository walletRepository;
    private WalletService walletService;

    public WalletController(ModelMapper modelMapper, WalletRepository walletRepository, WalletService walletService) {
        this.modelMapper = modelMapper;
        this.walletRepository = walletRepository;
        this.walletService = walletService;
    }
    @PutMapping("/payByCreditOfAccount/{customerOrderId}/{expertId}")
    public ResponseEntity<String> payByCreditOfAccount(@Valid @PathVariable Integer customerOrderId, @PathVariable Integer expertId) {
            walletService.payByCreditOfAccount(customerOrderId,expertId);
            return ResponseEntity.ok("send");
    }
    @PutMapping("/payByCard")
    public ResponseEntity<String> payByCard() {
        walletService.payByCard(852,452);
        return ResponseEntity.ok("paid!");
    }
}
