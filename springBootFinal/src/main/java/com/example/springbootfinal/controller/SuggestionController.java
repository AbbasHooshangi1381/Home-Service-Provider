package com.example.springbootfinal.controller;

import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.other.Suggestion;
import com.example.springbootfinal.domain.userEntity.Expert;
import com.example.springbootfinal.dto.Expert.SendOfferRequestDto;
import com.example.springbootfinal.repository.SubDutyRepository;
import com.example.springbootfinal.repository.SuggestionRepository;
import com.example.springbootfinal.service.SubDutyService;
import com.example.springbootfinal.service.SuggestionService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/suggestion")
public class SuggestionController {

    private SuggestionRepository suggestionRepository;
    private SuggestionService suggestionService;
    private ModelMapper modelMapper;

    public SuggestionController(SuggestionRepository suggestionRepository, SuggestionService suggestionService, ModelMapper modelMapper) {
        this.suggestionRepository = suggestionRepository;
        this.suggestionService = suggestionService;
        this.modelMapper = modelMapper;
    }
    @PostMapping("/sendOffer")
    public ResponseEntity<String> sendOfferForSubDuty(@RequestBody SendOfferRequestDto request) throws SQLException {
            suggestionService.sendOfferForSubDuty(request.getExpertId(), request.getCustomerOrderId(),
                    request.getSuggestionPrice(), request.getTimeOfWork(),request.getDurationTimeOfWork());
            return ResponseEntity.ok("send");
    }
    @GetMapping("/cusromerOrderList")
    public ResponseEntity<List<CustomerOrder>> getCustomerOrderList() {
        List<CustomerOrder> customerOrders = suggestionService.customerOrderList();
        if (!customerOrders.isEmpty()) {
            return ResponseEntity.ok(customerOrders);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/findAllPriceByCustomerOrderId/{customerOrderId}")
    public ResponseEntity<List<Suggestion>> findByCustomerIdOrderByProposedPriceDesc(@PathVariable Integer customerOrderId) {
        List<Suggestion> byCustomerIdOrderByProposedPriceDesc = suggestionService.findAllPriceByCustomerOrderId(customerOrderId);
        return ResponseEntity.ok(byCustomerIdOrderByProposedPriceDesc);
    }
}
