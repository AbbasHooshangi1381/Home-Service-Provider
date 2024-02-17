package com.example.springbootfinal.controller;

import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.other.Suggestion;
import com.example.springbootfinal.domain.userEntity.Expert;
import com.example.springbootfinal.dto.Admin.BaseResponseDto;
import com.example.springbootfinal.dto.Expert.CriteriaSearchDto;
import com.example.springbootfinal.dto.Expert.ExpertSaveDto;
import com.example.springbootfinal.dto.Expert.SendOfferRequestDto;
import com.example.springbootfinal.repository.ExpertRepository;
import com.example.springbootfinal.service.CustomerOrderService;
import com.example.springbootfinal.service.ExpertService;
import com.example.springbootfinal.service.SuggestionService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @Autowired
    ExpertService expertService;
    @Autowired
    ExpertRepository expertRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    CustomerOrderService customerOrderService;
    @Autowired
    SuggestionService suggestionService;


    @GetMapping("/login/{username}/{password}")
    public ResponseEntity<BaseResponseDto> checkExpert(@PathVariable String username, @PathVariable String password) {
        Expert expert = expertService.findByUserNameAndPassword(username, password).get();
        BaseResponseDto map = modelMapper.map(expert, BaseResponseDto.class);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PutMapping("/changePassword/{oldPassword}/{newPassword}")
    public ResponseEntity<String> changePassword(@PathVariable String oldPassword,
                                                 @PathVariable String newPassword) {
        expertService.changePassword(oldPassword, newPassword);
        return ResponseEntity.ok("password changed ! ");
    }

    @PutMapping("/confirmExpertStatusByAdmin/{id}")
    public ResponseEntity<BaseResponseDto> confirmExpertStatusByAdmin(@PathVariable Integer id) {
        Expert expert = expertService.changeStatusOfExpertByAdmin(id);
        BaseResponseDto map = modelMapper.map(expert, BaseResponseDto.class);
        return ResponseEntity.ok(map);
    }

    @PostMapping("/{expertId}")
    public ResponseEntity<String> addingPhotoOfDataBaseToFileInSystem(@PathVariable Integer expertId) throws IOException {
        byte[] bytes = expertService.saveImageByIdToSystem(expertId);
        if (bytes != null) {
            return ResponseEntity.ok("added to file");
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Failed to confirm expert status.");
        }
    }

    @PutMapping("/start/{orderId}")
    public ResponseEntity<String> startOrder(@PathVariable Integer orderId) {
        expertService.changeStatusOfOrderByExpertStarted(orderId);
        return ResponseEntity.ok("Order " + orderId + " has been started.");
    }

    @PutMapping("/finish/{suggestionId}/{timeOfFinishWork}")
    public ResponseEntity<String> finishOrder(@PathVariable Integer suggestionId, @PathVariable String timeOfFinishWork) {
        //"yyyy/MM/dd HH:mm:ss"
        expertService.changeStatusOfOrderByCustomerToFinish(suggestionId, timeOfFinishWork);
        return ResponseEntity.ok("Order " + suggestionId + " has been marked as finished.");
    }

    @GetMapping("/findAllExpertsByCriteria")
    public ResponseEntity<List<CriteriaSearchDto>> findAllExpertsByCriteria(@RequestBody Map<String, String> param) {
        List<Expert> allExpertsByCriteria = expertService.findAllExpertsByCriteria(param);
        List<CriteriaSearchDto> criteriaSearchDtoList = allExpertsByCriteria.stream()
                .map(expert -> modelMapper.map(expert, CriteriaSearchDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(criteriaSearchDtoList);
    }

    @GetMapping("/findExpertsByStar")
    public ResponseEntity<List<CriteriaSearchDto>> findExpertsByStar(@RequestParam Map<String, String> params) {
        List<Expert> allExpertsByCriteria = expertService.findExpertByStar(params);
        List<CriteriaSearchDto> criteriaSearchDtoList = allExpertsByCriteria.stream()
                .map(expert -> modelMapper.map(expert, CriteriaSearchDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(criteriaSearchDtoList);
    }

    ////////////////////////////////////////////////
    @GetMapping("/findOrdersOfExpertSubDuties/{expertId}")
    public ResponseEntity<List<CustomerOrder>> findOrdersOfExpertSubDuties(@PathVariable Integer expertId) {
        List<CustomerOrder> ordersByExpertId = customerOrderService.findOrdersByExpertId(expertId);
        return ResponseEntity.ok(ordersByExpertId);
    }

    @PostMapping("/sendOffer")
    public ResponseEntity<String> sendOfferForSubDuty(@Valid @RequestBody SendOfferRequestDto request) throws SQLException {
        suggestionService.sendOfferForSubDuty(request.getExpertId(), request.getCustomerOrderId(),
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

}