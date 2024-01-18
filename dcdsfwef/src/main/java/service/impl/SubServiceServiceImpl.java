package service.impl;

import base.service.impl.BaseEntityServiceImpl;
import domain.serviceEntity.SubService;
import repository.SubServiceRepository;
import service.SubServiceService;

public class SubServiceServiceImpl extends BaseEntityServiceImpl<Integer, SubService, SubServiceRepository>
        implements SubServiceService {
    public SubServiceServiceImpl(SubServiceRepository baseRepository) {
        super(baseRepository);
    }

    @Override
    public Boolean updateDescriptionField(Integer subServiceId, String newDescription) {
        return baseRepository.updateDescriptionField(subServiceId,newDescription);
    }

    @Override
    public Boolean updatePriceField(Integer subServiceId, Double price) {
        return baseRepository.updatePriceField(subServiceId, price);
    }

   /* @Override
    public Boolean deleteExpertFromSubService(Integer subServiceId) {
        return baseRepository.deleteExpertFromSubService(subServiceId);
    }*/

    @Override
    public Double priceOfSubService(Integer subServiceId) {
        return baseRepository.priceOfSubService(subServiceId);
    }
}
