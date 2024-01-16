package service.impl;

import base.service.impl.BaseEntityServiceImpl;
import domain.userEntity.Admin;
import repository.AdminRepository;
import service.AdminService;

import java.util.List;

public class AdminServiceImpl extends BaseEntityServiceImpl<Integer, Admin, AdminRepository>
        implements AdminService {
    public AdminServiceImpl(AdminRepository baseRepository) {
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
