package com.example.springbootfinal.service;

import com.example.springbootfinal.domain.enumurations.StatusOfOrder;
import com.example.springbootfinal.domain.other.CustomerOrder;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface CustomerOrderService {

    CustomerOrder saveOrder(String descriptionOfOrder, Double proposedPrice, String timeOfWork, String address,
                            StatusOfOrder waitingForSuggestExpert, Integer customerId, Integer subDutyId) throws SQLException;
    void changeStatusOfOrderByCustomerToWaitingToCome(Integer orderId);
    List<CustomerOrder> findOrdersByExpertId(Integer expertId);
    List<CustomerOrder> customerOrderListOfCustomer(Integer customerId,String statusOfOrder);
    List<CustomerOrder> customerOrderListOfExpert(Integer expertId,String statusOfOrder);
    List<CustomerOrder> orderByCriteria(Map<String, Object> filterParams);

}
