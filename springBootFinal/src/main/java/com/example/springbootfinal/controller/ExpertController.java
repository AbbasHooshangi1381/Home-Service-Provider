package com.example.springbootfinal.controller;

import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.userEntity.Expert;
import com.example.springbootfinal.dto.Expert.SendOfferRequestDto;
import com.example.springbootfinal.dto.customer.HistoryOfOrderDto;
import com.example.springbootfinal.dto.specification.RequestSpecificationDto;
import com.example.springbootfinal.repository.ExpertRepository;
import com.example.springbootfinal.service.*;
import com.example.springbootfinal.service.impl.FilterSpecificationImpl;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.util.List;

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
    private final FilterSpecificationImpl<Expert> filterSpecification;

    public ExpertController(ExpertService expertService, ExpertRepository expertRepository, ModelMapper modelMapper,
                            CustomerOrderService customerOrderService, SuggestionService suggestionService,
                            WalletService walletService, BaseUserService baseUserService,
                            FilterSpecificationImpl<Expert> filterSpecification) {
        this.expertService = expertService;
        this.expertRepository = expertRepository;
        this.modelMapper = modelMapper;
        this.customerOrderService = customerOrderService;
        this.suggestionService = suggestionService;
        this.walletService = walletService;
        this.baseUserService=baseUserService;
        this.filterSpecification=filterSpecification;
    }

    @PutMapping("/changePassword/{newPassword}")
    public ResponseEntity<String> changePassword(@PathVariable String newPassword) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        baseUserService.changePassword(name, newPassword);
        return ResponseEntity.ok("password changed ! ");
    }

    @GetMapping("/findOrdersOfExpertSubDuties")//سقارشات مربوط یه زیر حدمت متخصص
    public ResponseEntity<List<CustomerOrder>> findOrdersOfExpertSubDuties() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        List<CustomerOrder> ordersByExpertId = customerOrderService.findOrdersByExpertUserName(name);
        return ResponseEntity.ok(ordersByExpertId);
    }

    @PostMapping("/sendSuggestionForOrder")
    public ResponseEntity<String> sendSuggestionForOrder(@Valid @RequestBody SendOfferRequestDto request) throws SQLException {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        suggestionService.sendSuggestionForOrder(name, request.getCustomerOrderId(),
                request.getSuggestionPrice(), request.getTimeOfWork(), request.getDurationTimeOfWork());
        return ResponseEntity.ok("send");
    }

    @GetMapping("/showOrderToExpert")//نشان دادن سفارشات به متخصص
    public ResponseEntity<List<CustomerOrder>> showOrderToExpert() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        List<CustomerOrder> customerOrders = expertService.showOrderToExpert(name);
            return ResponseEntity.ok(customerOrders);

    }
    @PostMapping("/expertCriteria")
    public List<Expert> getExpert(@RequestBody RequestSpecificationDto requestSpecificationDto) {

        Specification<Expert> searchSpecificationDto = filterSpecification.getSearchSpecificationDto(requestSpecificationDto.getSearchSpecificationDto()
                , requestSpecificationDto.getGlobalOperator());
        return expertRepository.findAll(searchSpecificationDto);
        //firstName,lastName,email
    }
    @GetMapping("/historyOfOrderOfExpert")
    public ResponseEntity<List<CustomerOrder>> historyOfOrderOfExpert(@RequestBody HistoryOfOrderDto historyOfOrderDto) {
        List<CustomerOrder> customerOrders =
                customerOrderService.customerOrderListOfExpert(historyOfOrderDto.getId(), historyOfOrderDto.getStatusOfOrder());
        return ResponseEntity.ok(customerOrders);
    }

    @GetMapping("/creditOfExpert")
    public ResponseEntity<Double> creditOfExpert() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Double creditOfWalletByExpertId = walletService.findCreditOfWalletByExpertId(name);
        return ResponseEntity.ok(creditOfWalletByExpertId);
    }


    //////////////////////////////////////////
    @GetMapping("/showStarOfExpert")
    public ResponseEntity<Integer> showStarOfExpert() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
         Integer integer = expertService.showStarOfExpert(name);
        return ResponseEntity.ok(integer);

    }


}