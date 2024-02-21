package com.example.springbootfinal.controller;

import com.example.springbootfinal.domain.enumurations.StatusOfOrder;
import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.other.Suggestion;
import com.example.springbootfinal.domain.userEntity.Customer;
import com.example.springbootfinal.dto.Admin.BaseResponseDto;
import com.example.springbootfinal.dto.card.CardRequestDto;
import com.example.springbootfinal.dto.comments.CommentsRequestDto;
import com.example.springbootfinal.dto.customer.CriteriaSearchDtoOfCustomer;
import com.example.springbootfinal.dto.customer.FinishedDto;
import com.example.springbootfinal.dto.customer.StartedDto;
import com.example.springbootfinal.dto.customerOrder.CustomerOrderDTO;
import com.example.springbootfinal.dto.customerOrder.CustomerOrderResponseDto;
import com.example.springbootfinal.service.*;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    CustomerService customerService;
    CustomerOrderService customerOrderService;
    ModelMapper modelMapper;
    CommentService commentService;
    SuggestionService suggestionService;
    WalletService walletService;
    BaseUserService baseUserService;
    CardService cardService;

    public CustomerController(CustomerService customerService, CustomerOrderService customerOrderService,
                              ModelMapper modelMapper, CommentService commentService,
                              SuggestionService suggestionService, WalletService walletService,
                              BaseUserService baseUserService,CardService cardService) {
        this.customerService = customerService;
        this.customerOrderService = customerOrderService;
        this.modelMapper = modelMapper;
        this.commentService = commentService;
        this.suggestionService = suggestionService;
        this.walletService = walletService;
        this.baseUserService=baseUserService;
        this.cardService=cardService;
    }

    @PutMapping("/changePassword/{password}")
    public ResponseEntity<String> changePassword( @PathVariable String password) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        baseUserService.changePassword(name, password);
        return ResponseEntity.ok("pasword changed !");
    }


    //////////////////////////////////////////////////////////////////
    @PostMapping("/register-comments")
    public ResponseEntity<Integer> saveComments(@Valid @RequestBody CommentsRequestDto commentsRequestDto) {
        commentService.writCommentForExpert(commentsRequestDto.getCustomerOrderId(), commentsRequestDto.getExpertId(),
                commentsRequestDto.getComments(), commentsRequestDto.getStar());
        return ResponseEntity.status(HttpStatus.CREATED).body(commentsRequestDto.getStar());
    }

    @PostMapping("/saveOrder")
    public ResponseEntity<CustomerOrderResponseDto> saveOrder(@Valid @RequestBody CustomerOrderDTO customerOrderDto) throws Exception {
        String descriptionOfOrder = customerOrderDto.getDescriptionOfOrder();
        Double proposedPrice = customerOrderDto.getProposedPrice();
        String timeOfWork = customerOrderDto.getTimeOfWork();
        String address = customerOrderDto.getAddress();
        StatusOfOrder statusOfOrder = customerOrderDto.getStatusOfOrder();
        Integer customerId = customerOrderDto.getCustomerId();
        Integer subDutyId = customerOrderDto.getSubDutyId();
        CustomerOrder customerOrder = customerOrderService.saveOrder(descriptionOfOrder, proposedPrice, timeOfWork, address, statusOfOrder, customerId, subDutyId);
        CustomerOrderResponseDto responseDto = modelMapper.map(customerOrder, CustomerOrderResponseDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping("/changeStatusOfOrderByCustomerToWaitingToCome/{orderId}")
    public ResponseEntity<String> changeStatusOfOrderByCustomerToWaitingToCome(@PathVariable Integer orderId) {
        customerOrderService.changeStatusOfOrderByCustomerToWaitingToCome(orderId);
        return ResponseEntity.ok("Order status changed to WAITING_FOR_COMING_EXPERT");
    }
    @PutMapping("/changeStatusOfOrderTostarted")
    public ResponseEntity<String> startOrder(@RequestBody StartedDto startedDto) {
        customerOrderService.changeStatusOfOrderByExpertStarted(startedDto.getOrderId(),startedDto.getLocalDateTime());
        return ResponseEntity.ok("Order " + startedDto.getOrderId() + " has been started.");
    }
    @PutMapping("/changeStatusOfOrderToFinish")
    public ResponseEntity<String> finishOrder(@RequestBody FinishedDto finishedDto) {
        //"yyyy/MM/dd HH:mm:ss"
        customerOrderService.changeStatusOfOrderByCustomerToFinish(finishedDto.getOrderId(), finishedDto.getLocalDateTime());
        return ResponseEntity.ok("Order " + finishedDto.getOrderId() + " has been marked as finished.");
    }

    @GetMapping("/showSuggestionByPrice/{customerOrderId}")
    public ResponseEntity<List<Suggestion>> findByCustomerIdOrderByProposedPriceDesc(@PathVariable Integer customerOrderId) {
        List<Suggestion> byCustomerIdOrderByProposedPriceDesc = suggestionService.showSuggestionOrderByPriceOfSuggestions(customerOrderId);
        return ResponseEntity.ok(byCustomerIdOrderByProposedPriceDesc);
    }

    @GetMapping("/showSuggestionsByExpertStar/{customerOrderId}")
    public ResponseEntity<List<Suggestion>> findByCustomerOrderIdOrderByExpertStarsDesc(@PathVariable Integer customerOrderId) {
        List<Suggestion> byCustomerOrderIdOrderByExpertStarsDesc = suggestionService.showSuggestionOrderByExpertStars(customerOrderId);
        return ResponseEntity.ok(byCustomerOrderIdOrderByExpertStarsDesc);
    }

    @PutMapping("/payByCreditOfAccount/{customerOrderId}/{expertId}")
    public ResponseEntity<String> payByCreditOfAccount(@PathVariable Integer customerOrderId, @PathVariable Integer expertId) {
        walletService.payByCreditOfAccount(customerOrderId, expertId);
        return ResponseEntity.ok("send");
    }

    @PutMapping("/payByCard")
    @CrossOrigin
    public ResponseEntity<String> payByCard(@RequestBody CardRequestDto cardRequestDto) {
        walletService.payByCard(1102, 2952);
        return ResponseEntity.ok("paid!");
    }
    @PutMapping("/saveCard")
    public ResponseEntity<String> saveCard(@RequestBody CardRequestDto cardRequestDto) {
        cardService.saveCard(cardRequestDto.getCardNumber(),cardRequestDto.getCvv2(),cardRequestDto.getMonth()
        ,cardRequestDto.getPassword(),cardRequestDto.getYear());
        return ResponseEntity.ok("paid!");
    }

    ///////////////////////////////////new for insomnia/////////////////////////
    @GetMapping("/historyOfOrderOfCustomer/{customerId}/{statusOfOrder}")
    public ResponseEntity<List<CustomerOrder>> historyOfOrderOfCustomer(@PathVariable Integer customerId,
                                                                        @PathVariable String statusOfOrder) {
        List<CustomerOrder> customerOrders = customerOrderService.customerOrderListOfCustomer(customerId, statusOfOrder);
        return ResponseEntity.ok(customerOrders);
    }

    @GetMapping("/creditOfCustomer/{customerId}}")
    public ResponseEntity<Double> creditOfCustomer(@PathVariable Integer customerId) {
        Double creditOfWalletByCustomerId = walletService.findCreditOfWalletByCustomerId(customerId);
        return ResponseEntity.ok(creditOfWalletByCustomerId);
    }
}

