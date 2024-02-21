package com.example.springbootfinal.controller;

import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.other.ExpertRating;
import com.example.springbootfinal.domain.other.Suggestion;
import com.example.springbootfinal.domain.userEntity.Expert;
import com.example.springbootfinal.dto.Admin.BaseResponseDto;
import com.example.springbootfinal.dto.Expert.CriteriaSearchDto;
import com.example.springbootfinal.dto.Expert.ExpertSaveDto;
import com.example.springbootfinal.dto.Expert.SendOfferRequestDto;
import com.example.springbootfinal.repository.ExpertRepository;
import com.example.springbootfinal.service.*;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/expert")
public class ExpertController {
    ExpertService expertService;
    ExpertRepository expertRepository;
    ModelMapper modelMapper;
    CustomerOrderService customerOrderService;
    SuggestionService suggestionService;
    WalletService walletService;
    BaseUserService baseUserService;

    public ExpertController(ExpertService expertService, ExpertRepository expertRepository, ModelMapper modelMapper,
                            CustomerOrderService customerOrderService, SuggestionService suggestionService,
                            WalletService walletService, BaseUserService baseUserService) {
        this.expertService = expertService;
        this.expertRepository = expertRepository;
        this.modelMapper = modelMapper;
        this.customerOrderService = customerOrderService;
        this.suggestionService = suggestionService;
        this.walletService = walletService;
        this.baseUserService=baseUserService;
    }

    @PutMapping("/changePassword/{newPassword}")
    public ResponseEntity<String> changePassword(@PathVariable String newPassword) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        baseUserService.changePassword(name, newPassword);
        return ResponseEntity.ok("password changed ! ");
    }

    @PutMapping("/confirmExpertStatusByAdmin/{id}")
    public ResponseEntity<BaseResponseDto> confirmExpertStatusByAdmin(@PathVariable Integer id) {
        Expert expert = expertService.changeStatusOfExpertByAdmin(id);
        BaseResponseDto map = modelMapper.map(expert, BaseResponseDto.class);
        return ResponseEntity.ok(map);
    }


    ////////////////////////////////////////////////
    @GetMapping("/findOrdersOfExpertSubDuties/{expertId}")
    public ResponseEntity<List<CustomerOrder>> findOrdersOfExpertSubDuties(@PathVariable Integer expertId) {
        List<CustomerOrder> ordersByExpertId = customerOrderService.findOrdersByExpertId(expertId);
        return ResponseEntity.ok(ordersByExpertId);
    }

    @PostMapping("/sendOffer")
    public ResponseEntity<String> sendSuggestionForOrder(@Valid @RequestBody SendOfferRequestDto request) throws SQLException {
        suggestionService.sendSuggestionForOrder(request.getExpertId(), request.getCustomerOrderId(),
                request.getSuggestionPrice(), request.getTimeOfWork(), request.getDurationTimeOfWork());
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

    ///////////////////////////not save in insomnia /////////////////////
    @GetMapping("/historyOfOrderOfExpert/{expertId}/{statusOfOrder}")
    public ResponseEntity<List<CustomerOrder>> historyOfOrderOfCustomer(@PathVariable Integer expertId,
                                                                        @PathVariable String statusOfOrder) {
        List<CustomerOrder> customerOrders = customerOrderService.customerOrderListOfExpert(expertId, statusOfOrder);
        return ResponseEntity.ok(customerOrders);
    }

    @GetMapping("/creditOfExpert/{expertId}}")
    public ResponseEntity<Double> creditOfExpert(@PathVariable Integer expertId) {
        Double creditOfWalletByExpertId = walletService.findCreditOfWalletByExpertId(expertId);
        return ResponseEntity.ok(creditOfWalletByExpertId);
    }


}