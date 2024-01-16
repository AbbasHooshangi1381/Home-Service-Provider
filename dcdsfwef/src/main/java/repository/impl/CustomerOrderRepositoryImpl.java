package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import domain.other.CustomerOrder;
import repository.CustomerOrderRepository;
import repository.CustomerRepository;

import javax.persistence.EntityManager;

public class CustomerOrderRepositoryImpl extends BaseEntityRepositoryImpl<Integer, CustomerOrder> implements CustomerOrderRepository {
    public CustomerOrderRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<CustomerOrder> getEntityClass() {
        return CustomerOrder.class;
    }
}
