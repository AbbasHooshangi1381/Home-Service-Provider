package service;

import base.service.BaseEntityService;
import domain.userEntity.Admin;

import java.util.List;

public interface AdminService extends BaseEntityService<Integer, Admin> {
    List<String> showEmail();

    Boolean changePassword(Integer id , String newPassword);


}
