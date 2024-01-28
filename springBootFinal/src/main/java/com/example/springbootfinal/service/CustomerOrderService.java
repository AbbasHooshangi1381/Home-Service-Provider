package com.example.springbootfinal.service;

import com.example.springbootfinal.domain.other.CustomerOrder;

import java.sql.SQLException;
import java.util.List;

public interface CustomerOrderService {

    void saveOrder(CustomerOrder customerOrder,Integer customerId,Integer subDutyId)  throws SQLException;

    List<CustomerOrder> findByCustomerIdOrderByProposedPriceDesc(int customerId);

    List<CustomerOrder> findByCustomerIdOrderByExpertStarsDesc(int customerId);

    void changeStatusOfOrderByCustomerToWaitingToCome(Integer orderId);

    void changeStatusOfOrderByCustomerStarted(Integer orderId);

    void changeStatusOfOrderByCustomerToFinish(Integer orderId);
}
