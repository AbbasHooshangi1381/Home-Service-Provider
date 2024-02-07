package com.example.springbootfinal.controller;

import com.example.springbootfinal.domain.userEntity.Expert;
import com.example.springbootfinal.dto.Admin.BaseResponseDto;
import com.example.springbootfinal.dto.Expert.ExpertSaveDto;
import com.example.springbootfinal.dto.Expert.RequestDto;
import com.example.springbootfinal.repository.ExpertRepository;
import com.example.springbootfinal.service.ExpertService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
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
        Expert expert = expertService.findByUserNameAndPassword(username, password).get();
            BaseResponseDto map = modelMapper.map(expert, BaseResponseDto.class);
            return new ResponseEntity<>(map, HttpStatus.OK);
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
    @PutMapping("/finish/{suggestionId}/{timeOfFinishWork}")
    public ResponseEntity<String> finishOrder(@PathVariable Integer suggestionId ,@PathVariable String timeOfFinishWork) {
        //"yyyy/MM/dd HH:mm:ss"
        expertService.changeStatusOfOrderByCustomerToFinish(suggestionId,timeOfFinishWork);
        return ResponseEntity.ok("Order " + suggestionId + " has been marked as finished.");
    }



}