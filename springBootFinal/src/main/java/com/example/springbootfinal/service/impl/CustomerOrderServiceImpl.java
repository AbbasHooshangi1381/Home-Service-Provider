package com.example.springbootfinal.service.impl;

import com.example.springbootfinal.domain.enumurations.StatusOfOrder;
import com.example.springbootfinal.domain.other.CustomerOrder;
import com.example.springbootfinal.domain.serviceEntity.SubDuty;
import com.example.springbootfinal.domain.userEntity.Customer;
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
    SubDutyRepository subDutyRepository;
    CustomerOrderRepository customerOrderRepository;
    DutyRepository dutyRepository;


    @Override
    public void saveOrder(CustomerOrder customerOrder,Integer customerId,Integer subDutyId) throws SQLException {
        dutyRepository.findAll().forEach(System.out::println);
        subDutyRepository.findAll().forEach(System.out::println);

        CustomerOrder customerOrder1 = new CustomerOrder();
        Customer customer = customerRepository.findById(customerId).orElse(null);
        SubDuty subDuty = subDutyRepository.findById(subDutyId).orElse(null);
        String descriptionOfOrder = "you should it ! ";
        double proposedPrice = 8000.00;
        Optional<SubDuty> bargh = subDutyRepository.findBySubServiceName("bargh");
        Double fixPrice = bargh.get().getPrice();
        Double validatedPrice = null;
        if (proposedPrice >= fixPrice) {
            validatedPrice = proposedPrice;
        } else {
            System.out.println("your price is under the lowest price");
        }
        String timeOfWork;
        timeOfWork = checkAndRegisterTimeOfLoan("1402/11/30");
        String address = "mashhad";
        StatusOfOrder waitingForSuggestExpert = StatusOfOrder.WAITING_FOR_SELECT_EXPERT;
        customerOrder.setCustomer(customer);
        customerOrder.setDescriptionOfOrder(descriptionOfOrder);
        customerOrder.setAddress(address);
        customerOrder.setStatusOfOrder(waitingForSuggestExpert);
        customerOrder.setProposedPrice(validatedPrice);
        customerOrder.setSubService(subDuty);
        customerOrder.setTimeOfDoing(timeOfWork);

        customerOrderRepository.save(customerOrder);
    }

    @Override
    public List<CustomerOrder> findByCustomerIdOrderByProposedPriceDesc(int customerId) {
        List<CustomerOrder> customerOrders = customerOrderRepository.findByCustomerIdOrderByProposedPriceDesc(customerId);
        for (CustomerOrder customerOrder : customerOrders) {
            System.out.println(customerOrder);
        }
        return customerOrders;
    }

    @Override
    public List<CustomerOrder> findByCustomerIdOrderByExpertStarsDesc(int customerId) {
        List<CustomerOrder> customerOrders = customerOrderRepository.findByCustomerIdOrderByExpertStarsDesc(customerId);
        for (CustomerOrder customerOrder : customerOrders) {
            System.out.println(customerOrder);
        }
        return customerOrders;
    }

    @Override
    public void changeStatusOfOrderByCustomerToWaitingToCome(Integer orderId) {
        Optional<CustomerOrder> byId = customerOrderRepository.findById(orderId);
        byId.ifPresent(customerOrder -> {
            customerOrder.setStatusOfOrder(StatusOfOrder.WAITING_FOR_COMING_EXPERT);
            customerOrderRepository.save(customerOrder);
        });
    }

    @Override
    public void changeStatusOfOrderByCustomerStarted(Integer orderId) {
        Optional<CustomerOrder> byId = customerOrderRepository.findById(orderId);
        byId.ifPresent(customerOrder -> {
            customerOrder.setStatusOfOrder(StatusOfOrder.STARTED);
            customerOrderRepository.save(customerOrder);
        });
    }

    @Override
    public void changeStatusOfOrderByCustomerToFinish(Integer orderId) {
        Optional<CustomerOrder> byId = customerOrderRepository.findById(orderId);
        byId.ifPresent(customerOrder -> {
            customerOrder.setStatusOfOrder(StatusOfOrder.DONE);
            customerOrderRepository.save(customerOrder);
        });
    }

}
