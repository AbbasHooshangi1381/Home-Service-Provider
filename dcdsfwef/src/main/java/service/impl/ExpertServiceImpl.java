package service.impl;

import base.service.impl.BaseEntityServiceImpl;
import domain.userEntity.Expert;
import repository.ExpertRepository;
import service.ExpertService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExpertServiceImpl extends BaseEntityServiceImpl<Integer, Expert, ExpertRepository>
        implements ExpertService {
    public ExpertServiceImpl(ExpertRepository baseRepository) {
        super(baseRepository);
    }


    @Override
    public List<String> showEmail() {
        return baseRepository.showEmail();
    }


    @Override
    public Boolean changeStatus(Integer id) {
        return baseRepository.changeStatus(id);
    }

    @Override
    public Boolean changePassword(Integer id, String newPassword) {
        return baseRepository.changePassword(id, newPassword);
    }

    @Override
    public byte[] saveImageByIdToSystem(Integer id) {
        return baseRepository.saveImageByIdToSystem(id);

    }

}
