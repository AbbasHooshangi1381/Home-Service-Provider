package service.impl;

import base.service.impl.BaseEntityServiceImpl;
import domain.userEntity.Customer;
import repository.CustomerRepository;
import service.CustomerService;

import java.util.List;

public class CustomerServiceImpl extends BaseEntityServiceImpl<Integer, Customer, CustomerRepository>
        implements CustomerService {
    public CustomerServiceImpl(CustomerRepository baseRepository) {
        super(baseRepository);
    }


    @Override
    public List<String> showEmail() {
        return baseRepository.showEmail();
    }

    @Override
    public Boolean changePassword(Integer id, String newPassword) {
        return baseRepository.changePassword(id, newPassword);
    }
}
