package com.example.springbootfinal.controller;

import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.other.ExpertRating;
import com.example.springbootfinal.domain.serviceEntity.Duty;
import com.example.springbootfinal.domain.serviceEntity.SubDuty;
import com.example.springbootfinal.domain.userEntity.Admin;
import com.example.springbootfinal.domain.userEntity.BaseUser;
import com.example.springbootfinal.domain.userEntity.Customer;
import com.example.springbootfinal.domain.userEntity.Expert;
import com.example.springbootfinal.dto.Admin.*;
import com.example.springbootfinal.dto.customer.CriteriaSearchDtoOfCustomer;
import com.example.springbootfinal.dto.subDity.SubDutyResponseDto;
import com.example.springbootfinal.dto.subDity.SubDutySaveRequestDto;
import com.example.springbootfinal.repository.ExpertRepository;
import com.example.springbootfinal.service.*;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@SuppressWarnings("unused")
public class AdminController {

    private final AdminService adminService;
    private final ModelMapper modelMapper;
    private final DutyService dutyService;
    private final SubDutyService subDutyService;
    private final BaseUserService baseUserService;
    private final CustomerOrderService customerOrderService;
    private final ExpertService expertService;
    private final ExpertRepository expertRepository;
    private final CustomerService  customerService;

    public AdminController(AdminService adminService, ModelMapper modelMapper, DutyService dutyService,
                           SubDutyService subDutyService, BaseUserService baseUserService,
                           CustomerOrderService customerOrderService,
                           ExpertService expertService,ExpertRepository expertRepository,CustomerService customerService) {
        this.adminService = adminService;
        this.modelMapper = modelMapper;
        this.dutyService = dutyService;
        this.subDutyService = subDutyService;
        this.baseUserService = baseUserService;
        this.customerOrderService = customerOrderService;
        this.expertService=expertService;
        this.expertRepository=expertRepository;
        this.customerService=customerService;
    }

    @PutMapping("/changePassword//{newPassword}")
    public ResponseEntity<String> changePassword(@PathVariable String newPassword) {
         String name = SecurityContextHolder.getContext().getAuthentication().getName();
        baseUserService.changePassword(name, newPassword);
        return ResponseEntity.ok("password changed ! ");
    }

    //////////////////////////////////////////////////////////////////
    @PostMapping("/saveDuty/{saveDuty}")
    public ResponseEntity<String> saveExpert(@PathVariable String saveDuty) {
        Duty duty = dutyService.saveServiceByAdmin(saveDuty);

        if (duty == null) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>("saved!", HttpStatus.CREATED);
    }

    @PostMapping("/saveSubDuty")
    public ResponseEntity<SubDutyResponseDto> saveSubDuty(@Valid @RequestBody SubDutySaveRequestDto subDutySaveDto) {
        Integer dutyId = subDutySaveDto.getDutyId();
        Double priceOfSubDuty = subDutySaveDto.getPriceOfSubDuty();
        String description = subDutySaveDto.getDescription();
        String subServiceName = subDutySaveDto.getSubServiceName();
        SubDuty subDuty = subDutyService.saveSubDutyByAdmin(dutyId, subServiceName, priceOfSubDuty, description);
        if (subDuty==null){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        SubDutyResponseDto map = modelMapper.map(subDuty, SubDutyResponseDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(map);
    }
    @PutMapping("/changeDescriptionOfSubDuty/{subDutyId}/{newDescription}")
    public ResponseEntity<String> changeDescriptionOfSubDuty( @PathVariable Integer subDutyId, @PathVariable String newDescription) {
        subDutyService.changeDescriptionOfSubDuty(subDutyId, newDescription);
        return ResponseEntity.ok("Description of SubDuty with ID " + subDutyId + " has been changed.");
    }
    @PutMapping("/changePriceOfSubDuty/{subDutyId}/{newPrice}")
    public ResponseEntity<String> changePriceOfSubDuty( @PathVariable Integer subDutyId, @PathVariable Double newPrice){
        subDutyService.changePriceOfSubDutyByAdmin(subDutyId,newPrice);
        return ResponseEntity.ok("price of subDuty with ID"+subDutyId+"has been changed");
    }
    @PutMapping("/registerExpertInOneSubDuty/{expertId}/{subServiceId}")
    public ResponseEntity<String> registerExpertInOneSubDuty( @PathVariable Integer expertId, @PathVariable Integer subServiceId) {
        subDutyService.registerExpertInOneSubDuty(expertId, subServiceId);
        return ResponseEntity.ok("Expert with ID " + expertId + " added to SubService with ID " + subServiceId);
    }
    @PutMapping("/deleteExpertInSubDutyField/{expertId}/{subServiceId}")
    public ResponseEntity<String> deleteExpertInSubDutyField( @PathVariable Integer expertId,@PathVariable Integer subServiceId){
        subDutyService.deleteExpertInSubDutyField(expertId,subServiceId);
        return ResponseEntity.ok(" subDuty with ID"+subServiceId+"has deleted ");
    }
    @PutMapping("/confirmExpertStatusByAdmin/{id}")
    public ResponseEntity<BaseResponseDto> confirmExpertStatusByAdmin(@PathVariable Integer id) {
        Expert expert = expertService.changeStatusOfExpertByAdmin(id);
        BaseResponseDto map = modelMapper.map(expert, BaseResponseDto.class);
        return ResponseEntity.ok(map);
    }

    @GetMapping("/findAllExpertByCriteria")
    public List<CriteriaSearchDtoOfCustomer> findAllExpertByCriteria(@Valid @RequestBody Map<String, String> param) {
        List<CriteriaSearchDtoOfCustomer> criteriaSearchDtoOfCustomerList = new ArrayList<>();
        List<Expert> allSpecialistsByCriteria = expertService.findAllExpertByCriteria(param);
        for (Expert s : allSpecialistsByCriteria) {
            CriteriaSearchDtoOfCustomer map = modelMapper.map(s, CriteriaSearchDtoOfCustomer.class);
            criteriaSearchDtoOfCustomerList.add(map);
        }
        return criteriaSearchDtoOfCustomerList;
    }

    @GetMapping("/findExpertsBySubDuty/{subDutyName}")
    public List<Expert> findExpertsBySubDuty(@PathVariable String subDutyName) {
        return expertService.findExpertsBySubDuty(subDutyName);
    }

    @GetMapping("/findExpertRatings")
    public ExpertRating findExpertRatings() {
        return expertService.findExpertRatings();
    }

    @GetMapping("/findAllCustomertByCriteria")
    public List<CriteriaSearchDtoOfCustomer> findAllCustomerByCriteria(@Valid @RequestBody Map<String, String> param) {
        List<CriteriaSearchDtoOfCustomer> criteriaSearchDtoOfCustomerList = new ArrayList<>();
        List<Customer> allSpecialistsByCriteria = customerService.findAllCustomersByCriteria(param);
        for (Customer s : allSpecialistsByCriteria) {
            CriteriaSearchDtoOfCustomer map = modelMapper.map(s, CriteriaSearchDtoOfCustomer.class);
            criteriaSearchDtoOfCustomerList.add(map);
        }
        return criteriaSearchDtoOfCustomerList;
    }

    /////////////////////////////new to insomnia///////////////////////////
    @PostMapping("/reportOrder")
    public List<CustomerOrder> orderByCriteria(@RequestBody Map<String, Object> criteria) {
        return customerOrderService.orderByCriteria(criteria);
    }
    /*{
    "startDate": "2024-01-01T00:00:00",
    "endDate": "2024-01-31T23:59:59",
    "status": "DONE",
    "subDuty": 5,
    "expertIdWithDone": 10,
    "customerIdWithDone": 20
}*/

    @PostMapping("/reportUsers")
    public List<BaseUser> generateReport(@RequestBody Map<String, Object> criteria) {
        return baseUserService.generateReport(criteria);
    }
    /*{
    "userId": 123,
    "dateOfSigningInAfter": "2024-01-01",
    "minOrdersCount": 5,
    "minSuggestionsCount": 2
}*/

}
