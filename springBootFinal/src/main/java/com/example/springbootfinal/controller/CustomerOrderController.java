package com.example.springbootfinal.controller;

import com.example.springbootfinal.config.ModelMapperConfig;
import com.example.springbootfinal.domain.enumurations.StatusOfOrder;
import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.serviceEntity.SubDuty;
import com.example.springbootfinal.domain.userEntity.Customer;
import com.example.springbootfinal.dto.customerOrder.CustomerOrderDTO;
import com.example.springbootfinal.dto.customerOrder.CustomerOrderResponseDto;
import com.example.springbootfinal.repository.CustomerOrderRepository;
import com.example.springbootfinal.repository.CustomerRepository;
import com.example.springbootfinal.repository.DutyRepository;
import com.example.springbootfinal.repository.SubDutyRepository;
import com.example.springbootfinal.service.CustomerOrderService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customerOrder")
public class CustomerOrderController {
    private CustomerOrderService customerOrderService;
    private CustomerOrderRepository customerOrderRepository;
    private ModelMapper modelMapper;

    public CustomerOrderController(CustomerOrderService customerOrderService,
                                   CustomerOrderRepository customerOrderRepository, ModelMapper modelMapper) {
        this.customerOrderService = customerOrderService;
        this.customerOrderRepository = customerOrderRepository;
        this.modelMapper = modelMapper;
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
        CustomerOrderResponseDto responseDto = modelMapper.map(customerOrder,CustomerOrderResponseDto.class);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping("/changeStatusOfOrderByCustomerToWaitingToCome/{orderId}")
    public ResponseEntity<String> changeStatusOfOrderByCustomerToWaitingToCome( @PathVariable Integer orderId) {
        customerOrderService.changeStatusOfOrderByCustomerToWaitingToCome(orderId);
        return ResponseEntity.ok("Order status changed to WAITING_FOR_COMING_EXPERT");
    }

    @GetMapping("/findOrdersOfExpertSubDuties/{expertId}")
    public ResponseEntity<List<CustomerOrder>> findOrdersOfExpertSubDuties(@PathVariable Integer expertId) {
        List<CustomerOrder> ordersByExpertId = customerOrderService.findOrdersByExpertId(expertId);
        return ResponseEntity.ok(ordersByExpertId);
    }
}
