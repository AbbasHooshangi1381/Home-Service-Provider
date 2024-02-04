package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.enumurations.StatusOfOrder;
import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.serviceEntity.SubDuty;
import com.example.springbootfinal.domain.userEntity.Customer;
import com.example.springbootfinal.exception.NotFoundException;
import com.example.springbootfinal.repository.CustomerOrderRepository;
import com.example.springbootfinal.repository.CustomerRepository;
import com.example.springbootfinal.repository.DutyRepository;
import com.example.springbootfinal.repository.SubDutyRepository;
import com.example.springbootfinal.service.CustomerOrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static com.example.springbootfinal.service.impl.SubDutyServiceImpl.checkAndRegisterTimeOfLoan;

@Service
@Transactional
public class CustomerOrderServiceImpl implements CustomerOrderService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    SubDutyRepository subDutyRepository;
    @Autowired
    CustomerOrderRepository customerOrderRepository;
    @Autowired
    DutyRepository dutyRepository;

    @Override
    public CustomerOrder saveOrder(String descriptionOfOrder, double proposedPrice, String timeOfWork, String address,
                                   StatusOfOrder waitingForSuggestExpert, Integer customerId, Integer subDutyId) throws SQLException {
        dutyRepository.findAll().forEach(System.out::println);
        subDutyRepository.findAll().forEach(System.out::println);

        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new NotFoundException("Customer not found with ID: " + customerId));
        SubDuty subDuty = subDutyRepository.findById(subDutyId).orElseThrow(() -> new NotFoundException("SubDuty not found with ID: " + subDutyId));

        Optional<SubDuty> bargh = subDutyRepository.findBySubServiceName("gaz");
        Double fixPrice = bargh.get().getPrice();

        Double validatedPrice = validatePrice(proposedPrice, fixPrice);

        checkAndRegisterTimeOfLoan(timeOfWork);

        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setCustomer(customer);
        customerOrder.setDescriptionOfOrder(descriptionOfOrder);
        customerOrder.setAddress(address);
        customerOrder.setStatusOfOrder(waitingForSuggestExpert);
        customerOrder.setProposedPrice(validatedPrice);
        customerOrder.setSubService(subDuty);
        customerOrder.setTimeOfDoing(timeOfWork);

        return customerOrderRepository.save(customerOrder);
    }

    @Override
    public List<CustomerOrder> findByCustomerIdOrderByProposedPriceDesc(int customerId) {
        List<CustomerOrder> customerOrders = customerOrderRepository.findByCustomerIdOrderByProposedPriceDesc(customerId);
        if (customerOrders.isEmpty()) {
            throw new NotFoundException("i can not find this customer order");
        } else {
            for (CustomerOrder customerOrder : customerOrders) {
                System.out.println(customerOrder);
            }
        }
        return customerOrders;
    }

    @Override
    public List<CustomerOrder> findByCustomerIdOrderByExpertStarsDesc(int customerId) {
        List<CustomerOrder> customerOrders = customerOrderRepository.findByCustomerIdOrderByExpertStarsDesc(customerId);
        if (customerOrders.isEmpty()) {
            throw new NotFoundException("i can not find this customer order");
        } else {
            for (CustomerOrder customerOrder : customerOrders) {
                System.out.println(customerOrder);
            }
        }
        return customerOrders;

    }

    @Override
    public List<CustomerOrder> findCustomerOrderOfOneSubDuty(String subDuty) {
         List<CustomerOrder> bySubServiceName = customerOrderRepository.findBySubServiceName(subDuty);
         if (bySubServiceName.isEmpty()){
             System.out.println(" i dint have order for this subDuty");
         }
        return bySubServiceName;
    }

    @Override
    public void changeStatusOfOrderByCustomerToWaitingToCome(Integer orderId) {
        Optional<CustomerOrder> byId = customerOrderRepository.findById(orderId);
        if (byId.isEmpty()) {
            throw new NotFoundException("i can not find this order ");
        } else {
            byId.ifPresent(customerOrder -> {
                customerOrder.setStatusOfOrder(StatusOfOrder.WAITING_FOR_COMING_EXPERT);
                customerOrderRepository.save(customerOrder);
            });
        }
    }

    public Double validatePrice(double proposedPrice, double fixPrice) {
        Double validatedPrice = null;
        if (proposedPrice >= fixPrice) {
            validatedPrice = proposedPrice;
        } else {
            System.out.println("Your price is under the lowest price.");
        }
        return validatedPrice;
    }
}
