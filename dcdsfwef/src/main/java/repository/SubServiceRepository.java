package repository;

import base.repository.BaseEntityRepository;
import domain.serviceEntity.SubService;

public interface SubServiceRepository extends BaseEntityRepository<Integer, SubService> {

    Boolean updateDescriptionField(Integer subServiceId, String newDescription);


    Boolean updatePriceField(Integer subServiceId, Double price);

    Boolean deleteExpertFromSubService(Integer subServiceId);

}
