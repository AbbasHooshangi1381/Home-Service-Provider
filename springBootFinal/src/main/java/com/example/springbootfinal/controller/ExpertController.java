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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/expert")
public class ExpertController {
    @Autowired
    ExpertService expertService;
    @Autowired
    ExpertRepository expertRepository;
    @Autowired
    ModelMapper modelMapper;


    @PostMapping("/register-Expert")
    public ResponseEntity<BaseResponseDto> saveExpert(@RequestBody ExpertSaveDto expertSaveDto) throws IOException {
        Expert savedExpert = expertService.saveExpert(expertSaveDto.getFirstName(), expertSaveDto.getLastName(),
                expertSaveDto.getEmail(), expertSaveDto.getUserName(), expertSaveDto.getFilePath());

        BaseResponseDto map = modelMapper.map(savedExpert, BaseResponseDto.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(map);
    }

    @GetMapping("/login/{username}/{password}")
    public ResponseEntity<BaseResponseDto> checkExpert(@PathVariable String username, @PathVariable String password) {
        Expert expert = expertService.findByUserNameAndPassword(username, password).orElse(null);
        if (expert != null) {
            BaseResponseDto map = modelMapper.map(expert, BaseResponseDto.class);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/changePassword/{id}/{newPassword}")
    public ResponseEntity<String> changePassword(@PathVariable Integer id,
                                                 @PathVariable String newPassword) {
        expertService.changePassword(id, newPassword);

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
    public ResponseEntity<String> startOrder(@PathVariable Integer orderId ) {
        expertService.changeStatusOfOrderByExpertStarted(orderId);
        return ResponseEntity.ok("Order " + orderId + " has been started.");
    }

    @PutMapping("/finish/{orderId}/{timeOfFinishWork}")
    public ResponseEntity<String> finishOrder(@PathVariable Integer orderId ,@PathVariable String timeOfFinishWork) {
        //"yyyy/MM/dd HH:mm:ss"
        expertService.changeStatusOfOrderByCustomerToFinish(orderId,timeOfFinishWork);
        return ResponseEntity.ok("Order " + orderId + " has been marked as finished.");
    }
}