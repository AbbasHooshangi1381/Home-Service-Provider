package repository;

import base.repository.BaseEntityRepository;
import domain.serviceEntity.SubService;
import domain.userEntity.Admin;

import java.util.List;

public interface AdminRepository extends BaseEntityRepository<Integer, Admin> {
    List<String> showEmail();

    Boolean changePassword(Integer id , String newPassword);


}
