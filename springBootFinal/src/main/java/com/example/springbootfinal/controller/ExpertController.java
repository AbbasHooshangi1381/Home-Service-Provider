package com.example.springbootfinal.controller;

import com.example.springbootfinal.domain.other.Suggestion;
import com.example.springbootfinal.domain.userEntity.Expert;
import com.example.springbootfinal.dto.Admin.BaseResponseDto;
import com.example.springbootfinal.dto.Expert.CriteriaSearchDto;
import com.example.springbootfinal.dto.Expert.ExpertSaveDto;
import com.example.springbootfinal.repository.ExpertRepository;
import com.example.springbootfinal.service.ExpertService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


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
    public ResponseEntity<BaseResponseDto> saveExpert(@Valid @RequestBody ExpertSaveDto expertSaveDto) throws IOException {
        Expert savedExpert = expertService.saveExpert(expertSaveDto.getFirstName(), expertSaveDto.getLastName(),
                expertSaveDto.getEmail(), expertSaveDto.getUserName(), expertSaveDto.getFilePath());

        BaseResponseDto map = modelMapper.map(savedExpert, BaseResponseDto.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(map);
    }

    @GetMapping("/login/{username}/{password}")
    public ResponseEntity<BaseResponseDto> checkExpert( String username, @PathVariable String password) {
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

    @GetMapping("/findByCustomerIdOrderByExpertStarsDesc/{customerOrderId}")
    public ResponseEntity<List<Suggestion>> findByCustomerOrderIdOrderByExpertStarsDesc(@PathVariable Integer customerOrderId) {
        List<Suggestion> byCustomerOrderIdOrderByExpertStarsDesc = expertService.findByCustomerOrderIdOrderByExpertStarsDesc(customerOrderId);
        return ResponseEntity.ok(byCustomerOrderIdOrderByExpertStarsDesc);
    }

    @PostMapping("/findAllExpertByCriteria")
    public List<CriteriaSearchDto> findAllSpecialistsByCriteria(@RequestBody Map<String, String> param) {
        List<CriteriaSearchDto> criteriaSearchDtoList = new ArrayList<>();
        List<Expert> allSpecialistsByCriteria = expertService.findAllExpertsByCriteria(param);
        for (Expert s : allSpecialistsByCriteria) {
            CriteriaSearchDto criteriaSearchDto = modelMapper.map(s, CriteriaSearchDto.class);
            criteriaSearchDtoList.add(criteriaSearchDto);
        }
        return criteriaSearchDtoList;
    }


}