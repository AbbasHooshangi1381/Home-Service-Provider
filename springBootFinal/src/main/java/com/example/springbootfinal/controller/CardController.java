package com.example.springbootfinal.controller;

import com.example.springbootfinal.domain.userEntity.Customer;
import com.example.springbootfinal.dto.Admin.BaseResponseDto;
import com.example.springbootfinal.dto.Admin.BaseSaveDto;
import com.example.springbootfinal.dto.card.CardRequestDto;
import com.example.springbootfinal.service.CardService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
@AllArgsConstructor
public class CardController {
    @Autowired
    CardService cardService;

    @PostMapping("/saveCard")
    public ResponseEntity<String> saveCard(@Valid @RequestBody CardRequestDto cardRequestDto) {
        cardService.saveCard(cardRequestDto.getCardNumber()
                ,cardRequestDto.getCvv2(),
                cardRequestDto.getMonth(),
                cardRequestDto.getPassword(),
                cardRequestDto.getYear(),
                cardRequestDto.getCustomerId());

        return ResponseEntity.status(HttpStatus.CREATED).body("card saved!");
    }
}
