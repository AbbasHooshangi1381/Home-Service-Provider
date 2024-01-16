package service.impl;

import base.service.impl.BaseEntityServiceImpl;
import domain.other.CustomerOrder;
import repository.CustomerOrderRepository;
import service.CustomerOrderService;

public class CustomerOrderServiceImpl extends BaseEntityServiceImpl<Integer, CustomerOrder, CustomerOrderRepository>
        implements CustomerOrderService {
    public CustomerOrderServiceImpl(CustomerOrderRepository baseRepository) {
        super(baseRepository);
    }
}
