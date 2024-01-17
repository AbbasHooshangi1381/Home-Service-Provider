package repository;

import base.repository.BaseEntityRepository;
import domain.serviceEntity.SubService;
import domain.userEntity.Customer;
import domain.userEntity.Expert;

import java.util.List;

public interface ExpertRepository extends BaseEntityRepository<Integer, Expert> {

     List<String> showEmail();

     Boolean updateSubServiceWithExpert(Integer subServiceId, Integer expertId);

     Boolean changeStatus(Integer id);

     Boolean changePassword(Integer id , String newPassword);

     void savePhotoFromDatabase(String destinationPath, int expertId);

}
