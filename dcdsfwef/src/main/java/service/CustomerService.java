package service;

import base.service.BaseEntityService;
import domain.userEntity.Customer;

import java.util.List;

public interface CustomerService extends BaseEntityService<Integer, Customer> {

     List<String> showEmail();

    Boolean changePassword(Integer id , String newPassword);

}
