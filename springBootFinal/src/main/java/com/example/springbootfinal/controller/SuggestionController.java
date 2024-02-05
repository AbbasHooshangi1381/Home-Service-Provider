package com.example.springbootfinal.controller;

import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.other.Suggestion;
import com.example.springbootfinal.repository.SubDutyRepository;
import com.example.springbootfinal.repository.SuggestionRepository;
import com.example.springbootfinal.service.SubDutyService;
import com.example.springbootfinal.service.SuggestionService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/findByCustomerIdOrderByProposedPriceDesc/{customerOrderId}")
    public ResponseEntity<List<Suggestion>> findByCustomerIdOrderByProposedPriceDesc(@PathVariable Integer customerOrderId) {
        List<Suggestion> byCustomerIdOrderByProposedPriceDesc = suggestionService.findByCustomerIdOrderByProposedPriceDesc(customerOrderId);
        return ResponseEntity.ok(byCustomerIdOrderByProposedPriceDesc);
    }

    @GetMapping("/findByCustomerIdOrderByExpertStarsDesc/{customerOrderId}")
    public ResponseEntity<List<Suggestion>> findByCustomerOrderIdOrderByExpertStarsDesc(@PathVariable Integer customerOrderId) {
        List<Suggestion> byCustomerOrderIdOrderByExpertStarsDesc = suggestionRepository.findByCustomerOrderIdOrderByExpertStarsDesc(customerOrderId);
        return ResponseEntity.ok(byCustomerOrderIdOrderByExpertStarsDesc);
    }
}
