package com.example.springbootfinal.controller;

import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.userEntity.Admin;
import com.example.springbootfinal.domain.userEntity.Expert;
import com.example.springbootfinal.dto.Admin.BaseChangePasswordDto;
import com.example.springbootfinal.dto.Admin.BaseResponseDto;

import com.example.springbootfinal.dto.Expert.ExpertSaveDto;
import com.example.springbootfinal.dto.Expert.SendOfferRequestDto;
import com.example.springbootfinal.repository.ExpertRepository;
import com.example.springbootfinal.service.ExpertService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/expert")
public class ExpertController {

    ExpertService expertService;
    ExpertRepository expertRepository;
    ModelMapper modelMapper;

    @PostMapping("/register-Expert")
    public ResponseEntity<BaseResponseDto> saveExpert(@RequestBody ExpertSaveDto expertSaveDto) throws IOException {
        Expert savedExpert = expertService.saveExpert(expertSaveDto.getFirstName(), expertSaveDto.getLastName(),
                expertSaveDto.getEmail(), expertSaveDto.getUserName(), expertSaveDto.getFilePath());

        BaseResponseDto map = modelMapper.map(savedExpert, BaseResponseDto.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(map);
    }

    @GetMapping("/{username}/{password}")
    public ResponseEntity<ExpertSaveDto> checkExpert(@PathVariable String username, @PathVariable String password) {
        Expert expert = expertService.findByUserNameAndPassword(username, password).orElse(null);
        if (expert != null) {
            ExpertSaveDto map = modelMapper.map(expert, ExpertSaveDto.class);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
        public ResponseEntity<BaseResponseDto> changePassword(@PathVariable Integer id,
                                                            @RequestBody BaseChangePasswordDto expertChangePasswordDto) {
        Expert expert = expertRepository.findById(id).get();

        expert.setPassword(expertChangePasswordDto.getNewPassword());
        expertRepository.save(expert);

        BaseResponseDto map = modelMapper.map(expert, BaseResponseDto.class);

        return ResponseEntity.ok(map);
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> confirmExpertStatusByAdmin(@PathVariable Integer id) {
        try {
            expertService.changeStatusOfExpertByAdmin(id);
            return ResponseEntity.ok("Expert status confirmed successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to confirm expert status.");
        }
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

    @PostMapping("/sendOffer")
    public ResponseEntity<String> sendOfferForSubDuty(@RequestBody SendOfferRequestDto request) {
        try {
            expertService.sendOfferForSubDuty(request.getExpertId(), request.getCustomerOrderId(),
                    request.getSuggestionPrice(), request.getTimeOfWork());
            return ResponseEntity.ok("send");
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(" i can not send it");
        }
    }

    @GetMapping("/cusromerOrderList")
    public ResponseEntity<List<CustomerOrder>> getCustomerOrderList() {
        List<CustomerOrder> customerOrders = expertService.customerOrderList();
        if (!customerOrders.isEmpty()) {
            return ResponseEntity.ok(customerOrders);
        } else {
            return ResponseEntity.notFound().build();
        }


    }
}