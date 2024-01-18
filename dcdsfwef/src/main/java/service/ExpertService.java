package service;

import base.service.BaseEntityService;
import domain.userEntity.Expert;

import java.util.List;

public interface ExpertService extends BaseEntityService<Integer, Expert> {

     List<String> showEmail();

   //  Boolean updateSubServiceWithExpert(Integer subServiceId, Integer expertId);

     Boolean changeStatus(Integer id);

     Boolean changePassword(Integer id , String newPassword);

     void savePhotoFromDatabase(String destinationPath, Integer expertId);


}
