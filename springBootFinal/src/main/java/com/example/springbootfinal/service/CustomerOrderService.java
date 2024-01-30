package com.example.springbootfinal.service;

import com.example.springbootfinal.domain.enumurations.StatusOfOrder;
import com.example.springbootfinal.domain.other.CustomerOrder;

import java.sql.SQLException;
import java.util.List;

public interface CustomerOrderService {
    CustomerOrder saveOrder(String descriptionOfOrder, double proposedPrice, String timeOfWork, String address,
                            StatusOfOrder waitingForSuggestExpert   , Integer customerId, Integer subDutyId)  throws SQLException;
    List<CustomerOrder> findByCustomerIdOrderByProposedPriceDesc(int customerId);
    List<CustomerOrder> findByCustomerIdOrderByExpertStarsDesc(int customerId);
    void changeStatusOfOrderByCustomerToWaitingToCome(Integer orderId);
}
