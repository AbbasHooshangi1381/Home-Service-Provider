package service;

import base.service.BaseEntityService;
import domain.serviceEntity.SubService;

public interface SubServiceService extends BaseEntityService<Integer, SubService> {

    Boolean updateDescriptionField(Integer subServiceId, String newDescription);


    Boolean updatePriceField(Integer subServiceId, Double price);

  //  Boolean deleteExpertFromSubService(Integer subServiceId);

    Double priceOfSubService(Integer subServiceId);

}
