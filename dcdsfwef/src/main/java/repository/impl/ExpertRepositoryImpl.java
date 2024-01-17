package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import domain.enumurations.ExpertStatus;
import domain.serviceEntity.SubService;
import domain.userEntity.Expert;
import repository.ExpertRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class ExpertRepositoryImpl extends BaseEntityRepositoryImpl<Integer, Expert> implements ExpertRepository {


    public ExpertRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Expert> getEntityClass() {
        return Expert.class;
    }

    @Override
    public List<String> showEmail() {
        TypedQuery<String> query = entityManager.createQuery("SELECT e.email FROM Expert e", String.class);
        return query.getResultList();
    }

    @Override
    public Boolean updateSubServiceWithExpert(Integer subServiceId, Integer expertId) {
        try {
            beginTransaction();

            SubService subService = entityManager.find(SubService.class, subServiceId);
            Expert expert = entityManager.find(Expert.class, expertId);

            if (subService != null && expert != null) {
                subService.setExpert(expert);
                entityManager.merge(subService);
                commitTransaction();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean changeStatus(Integer id) {
        try {
            beginTransaction();

            Expert expert = entityManager.find(Expert.class, id);
            if (expert != null) {
                expert.setExpertStatus(ExpertStatus.CONFIRMED);
                entityManager.merge(expert);

                commitTransaction();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public Boolean changePassword(Integer id, String newPassword) {
        try {
            beginTransaction();

            Expert expert = entityManager.find(Expert.class, id);
            if (expert != null) {
                expert.setPassword(newPassword);
                entityManager.merge(expert);

                commitTransaction();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public void savePhotoFromDatabase(String destinationPath, int expertId) {
        try {
            Expert expert = entityManager.find(Expert.class, expertId);
            byte[] photoData = expert.getPersonalPhoto();

            if (photoData != null) {
                String fileName = "personalPhoto :" + expertId + ".jpg";
                String fullPath = destinationPath + File.separator + fileName;

                FileOutputStream fos = new FileOutputStream(fullPath);
                fos.write(photoData);
                fos.close();

                System.out.println("Photo saved successfully at: " + fullPath);
            } else {
                System.out.println("No photo found for expert with ID: " + expertId);
            }
        } catch (Exception e) {
            System.out.println("Failed to save photo: " + e.getMessage());
        }
    }
}
