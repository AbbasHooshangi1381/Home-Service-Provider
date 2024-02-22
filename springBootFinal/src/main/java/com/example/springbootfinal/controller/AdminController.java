package com.example.springbootfinal.controller;

import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.other.ExpertRating;
import com.example.springbootfinal.domain.serviceEntity.Duty;
import com.example.springbootfinal.domain.serviceEntity.SubDuty;
import com.example.springbootfinal.domain.userEntity.Customer;
import com.example.springbootfinal.domain.userEntity.Expert;
import com.example.springbootfinal.dto.Admin.*;
import com.example.springbootfinal.dto.customer.HistoryOfOrderDto;
import com.example.springbootfinal.dto.specification.RequestSpecificationDto;
import com.example.springbootfinal.dto.subDity.SubDutyResponseDto;
import com.example.springbootfinal.dto.subDity.SubDutySaveRequestDto;
import com.example.springbootfinal.repository.BaseUserRepository;
import com.example.springbootfinal.repository.CustomerOrderRepository;
import com.example.springbootfinal.repository.CustomerRepository;
import com.example.springbootfinal.repository.ExpertRepository;
import com.example.springbootfinal.service.*;
import com.example.springbootfinal.service.impl.FilterSpecificationImpl;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
    private final BaseUserRepository baseUserRepository;
    private final CustomerService customerService;
    private final FilterSpecificationImpl<Expert> filterSpecification;
    private final FilterSpecificationImpl<Customer> filterSpecificationCustomer;
    private final CustomerRepository customerRepository;
    private final CustomerOrderRepository customerOrderRepository;
    private final FilterSpecificationImpl<CustomerOrder> filterSpecificationCustomerOrder;

    public AdminController(AdminService adminService, ModelMapper modelMapper, DutyService dutyService,
                           SubDutyService subDutyService, BaseUserService baseUserService,
                           CustomerOrderService customerOrderService, ExpertService expertService,
                           ExpertRepository expertRepository, BaseUserRepository baseUserRepository,
                           CustomerService customerService, FilterSpecificationImpl<Expert> filterSpecification,
                           FilterSpecificationImpl<Customer> filterSpecificationCustomer,
                           CustomerRepository customerRepository, CustomerOrderRepository customerOrderRepository,
                           FilterSpecificationImpl<CustomerOrder> filterSpecificationCustomerOrder) {
        this.adminService = adminService;
        this.modelMapper = modelMapper;
        this.dutyService = dutyService;
        this.subDutyService = subDutyService;
        this.baseUserService = baseUserService;
        this.customerOrderService = customerOrderService;
        this.expertService = expertService;
        this.expertRepository = expertRepository;
        this.baseUserRepository = baseUserRepository;
        this.customerService = customerService;
        this.filterSpecification = filterSpecification;
        this.filterSpecificationCustomer = filterSpecificationCustomer;
        this.customerRepository = customerRepository;
        this.customerOrderRepository = customerOrderRepository;
        this.filterSpecificationCustomerOrder = filterSpecificationCustomerOrder;
    }

    @PostMapping("/expertCriteria")
    public List<Expert> getExpert(@RequestBody RequestSpecificationDto requestSpecificationDto) {

        Specification<Expert> searchSpecificationDto = filterSpecification.getSearchSpecificationDto(requestSpecificationDto.getSearchSpecificationDto()
                , requestSpecificationDto.getGlobalOperator());
        return expertRepository.findAll(searchSpecificationDto);
        //firstName,lastName,email
    }

    @PostMapping("/customerCriteria")
    public List<Customer> getCustomer(@RequestBody RequestSpecificationDto requestSpecificationDto) {

        Specification<Customer> searchSpecificationDto = filterSpecificationCustomer.getSearchSpecificationDto(requestSpecificationDto.getSearchSpecificationDto()
                , requestSpecificationDto.getGlobalOperator());
        return customerRepository.findAll(searchSpecificationDto);
    }

    @PutMapping("/changePassword/{newPassword}")
    public ResponseEntity<String> changePassword(@PathVariable String newPassword) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        baseUserService.changePassword(name, newPassword);
        return ResponseEntity.ok("password changed ! ");
    }

    @PostMapping("/saveDuty/{saveDuty}")
    public ResponseEntity<String> saveExpert(@PathVariable String saveDuty) {
        Duty duty = dutyService.saveServiceByAdmin(saveDuty);
        return new ResponseEntity<>("saved!", HttpStatus.CREATED);
    }

    @PostMapping("/saveSubDuty")
    public ResponseEntity<SubDutyResponseDto> saveSubDuty(@Valid @RequestBody SubDutySaveRequestDto subDutySaveDto) {
        Integer dutyId = subDutySaveDto.getDutyId();
        Double priceOfSubDuty = subDutySaveDto.getPriceOfSubDuty();
        String description = subDutySaveDto.getDescription();
        String subServiceName = subDutySaveDto.getSubServiceName();
        SubDuty subDuty = subDutyService.saveSubDutyByAdmin(dutyId, subServiceName, priceOfSubDuty, description);
        if (subDuty == null) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        SubDutyResponseDto map = modelMapper.map(subDuty, SubDutyResponseDto.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(map);
    }

    @PutMapping("/changeDescriptionOfSubDuty/{subDutyId}/{newDescription}")
    public ResponseEntity<String> changeDescriptionOfSubDuty(@PathVariable Integer subDutyId, @PathVariable String newDescription) {
        subDutyService.changeDescriptionOfSubDuty(subDutyId, newDescription);
        return ResponseEntity.ok("Description of SubDuty with ID " + subDutyId + " has been changed.");
    }

    @PutMapping("/changePriceOfSubDuty/{subDutyId}/{newPrice}")
    public ResponseEntity<String> changePriceOfSubDuty(@PathVariable Integer subDutyId, @PathVariable Double newPrice) {
        subDutyService.changePriceOfSubDutyByAdmin(subDutyId, newPrice);
        return ResponseEntity.ok("price of subDuty with ID" + subDutyId + "has been changed");
    }

    @PutMapping("/registerExpertInOneSubDuty/{expertId}/{subServiceId}")
    public ResponseEntity<String> registerExpertInOneSubDuty(@PathVariable Integer expertId, @PathVariable Integer subServiceId) {
        subDutyService.registerExpertInOneSubDuty(expertId, subServiceId);
        return ResponseEntity.ok("Expert with ID " + expertId + " added to SubService with ID " + subServiceId);
    }

    @PutMapping("/deleteExpertInSubDutyField/{expertId}/{subServiceId}")
    public ResponseEntity<String> deleteExpertInSubDutyField(@PathVariable Integer expertId, @PathVariable Integer subServiceId) {
        subDutyService.deleteExpertInSubDutyField(expertId, subServiceId);
        return ResponseEntity.ok(" subDuty with ID" + subServiceId + "has deleted ");
    }

    @PutMapping("/confirmExpertStatusByAdmin/{idOfExpert}")
    public ResponseEntity<BaseResponseDto> confirmExpertStatusByAdmin(@PathVariable Integer idOfExpert) {
        Expert expert = expertService.changeStatusOfExpertByAdmin(idOfExpert);
        BaseResponseDto map = modelMapper.map(expert, BaseResponseDto.class);
        return ResponseEntity.ok(map);
    }

    @GetMapping("/findExpertsBySubDuty/{subDutyName}")
    public List<Expert> findExpertsBySubDuty(@PathVariable String subDutyName) {
        return expertService.findExpertsBySubDuty(subDutyName);
    }

    @GetMapping("/findExpertRatings")
    public ExpertRating findExpertRatings() {
        return expertService.findExpertRatings();
    }

    @PostMapping("/CustomerOrderCriteria")
    public List<CustomerOrder> getCustomerOrder(@RequestBody RequestSpecificationDto requestSpecificationDto) {

        Specification<CustomerOrder> searchSpecificationDto = filterSpecificationCustomerOrder.
                getSearchSpecificationDto(requestSpecificationDto.getSearchSpecificationDto()
                        , requestSpecificationDto.getGlobalOperator());
        return customerOrderRepository.findAll(searchSpecificationDto);
        /*{
            "startDate": "2024-01-01T00:00:00 , endDate": "2024-01-31T23:59:59",
            "status": "DONE",
            "JoinBySubDutyName":"....
}*/
    }

    @GetMapping("/historyOfOrderOfCustomer")
    public ResponseEntity<List<CustomerOrder>> historyOfOrderOfCustomer(@RequestBody HistoryOfOrderDto historyOfOrderDto) {
        List<CustomerOrder> customerOrders = customerOrderService.
                customerOrderListOfCustomer(historyOfOrderDto.getId(), historyOfOrderDto.getStatusOfOrder());
        return ResponseEntity.ok(customerOrders);
    }

    @GetMapping("/historyOfOrderOfExpert")
    public ResponseEntity<List<CustomerOrder>> historyOfOrderOfExpert(@RequestBody HistoryOfOrderDto historyOfOrderDto) {
        List<CustomerOrder> customerOrders =
                customerOrderService.customerOrderListOfExpert(historyOfOrderDto.getId(), historyOfOrderDto.getStatusOfOrder());
        return ResponseEntity.ok(customerOrders);
    }


}
