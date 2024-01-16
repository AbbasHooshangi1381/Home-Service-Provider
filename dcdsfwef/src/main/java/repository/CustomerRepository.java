package repository;

import base.repository.BaseEntityRepository;
import domain.serviceEntity.SubService;
import domain.userEntity.Customer;

import java.util.List;

public interface CustomerRepository extends BaseEntityRepository<Integer, Customer> {

     List<String> showEmail();

     Boolean changePassword(Integer id , String newPassword);

}
