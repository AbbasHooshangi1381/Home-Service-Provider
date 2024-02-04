package com.example.springbootfinal.controller;

import com.example.springbootfinal.config.ModelMapperConfig;
import com.example.springbootfinal.domain.enumurations.StatusOfOrder;
import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.serviceEntity.SubDuty;
import com.example.springbootfinal.domain.userEntity.Customer;
import com.example.springbootfinal.dto.customerOrder.CustomerOrderDTO;
import com.example.springbootfinal.repository.CustomerOrderRepository;
import com.example.springbootfinal.repository.CustomerRepository;
import com.example.springbootfinal.repository.DutyRepository;
import com.example.springbootfinal.repository.SubDutyRepository;
import com.example.springbootfinal.service.CustomerOrderService;
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
    public ResponseEntity<CustomerOrder> saveOrder(@RequestBody CustomerOrderDTO customerOrderDto) throws Exception {
        String descriptionOfOrder = customerOrderDto.getDescriptionOfOrder();
        double proposedPrice = customerOrderDto.getProposedPrice();
        String timeOfWork = customerOrderDto.getTimeOfWork();
        String address = customerOrderDto.getAddress();
        StatusOfOrder statusOfOrder = customerOrderDto.getStatusOfOrder();
        Integer customerId = customerOrderDto.getCustomerId();
        Integer subDutyId = customerOrderDto.getSubDutyId();

        CustomerOrder customerOrder = customerOrderService.saveOrder(descriptionOfOrder, proposedPrice, timeOfWork, address, statusOfOrder, customerId, subDutyId);

        return new ResponseEntity<>(customerOrder, HttpStatus.CREATED);
    }

    @GetMapping("/findByCustomerIdOrderByProposedPriceDesc/{customerId}")
    public ResponseEntity<List<CustomerOrder>> findByCustomerIdOrderByProposedPriceDesc(@PathVariable int customerId) {
        List<CustomerOrder> customerOrders = customerOrderService.findByCustomerIdOrderByProposedPriceDesc(customerId);
        return ResponseEntity.ok(customerOrders);
    }

    @GetMapping("/findByCustomerIdOrderByExpertStarsDesc/{customerId}")
    public ResponseEntity<List<CustomerOrder>> findByCustomerIdOrderByExpertStarsDesc(@PathVariable int customerId) {
        List<CustomerOrder> customerOrders = customerOrderService.findByCustomerIdOrderByExpertStarsDesc(customerId);
        return ResponseEntity.ok(customerOrders);
    }

    @PutMapping("/changeStatusOfOrderByCustomerToWaitingToCome/{orderId}/change-status")
    public ResponseEntity<String> changeStatusOfOrderByCustomerToWaitingToCome(@PathVariable Integer orderId) {
        customerOrderService.changeStatusOfOrderByCustomerToWaitingToCome(orderId);
        return ResponseEntity.ok("Order status changed to WAITING_FOR_COMING_EXPERT");
    }
}
