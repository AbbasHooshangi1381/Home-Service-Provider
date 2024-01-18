package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import domain.other.Comments;
import domain.serviceEntity.SubService;
import repository.CommentsRepository;
import repository.SubServiceRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class SubServiceRepositoryImpl extends BaseEntityRepositoryImpl<Integer, SubService> implements SubServiceRepository {

    public SubServiceRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<SubService> getEntityClass() {
        return SubService.class;
    }


    @Override
    public Boolean updateDescriptionField(Integer subServiceId, String newDescription) {
        try {
            beginTransaction();
            SubService subService = entityManager.find(SubService.class, subServiceId);

            if (subService != null) {
                subService.setDescription(newDescription);
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
    public Boolean updatePriceField(Integer subServiceId, Double price) {
        try {
            beginTransaction();
            SubService subService = entityManager.find(SubService.class, subServiceId);

            if (subService != null) {
                subService.setPrice(price);
                entityManager.merge(subService);
                commitTransaction();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

 /*   @Override
    public Boolean deleteExpertFromSubService(Integer subServiceId) {
        try {
            beginTransaction();

            SubService subService = entityManager.find(SubService.class, subServiceId);

            if (subService != null) {
                subService.setExpert(null);
                entityManager.merge(subService);
                commitTransaction();

                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }*/

    @Override
    public Double priceOfSubService(Integer subServiceId) {
         TypedQuery<Double> query = entityManager.createQuery("SELECT s.price FROM SubService s " +
                 "where s.id=:subServiceId", Double.class)
                 .setParameter("subServiceId",subServiceId);
         return query.getSingleResult();
    }
}