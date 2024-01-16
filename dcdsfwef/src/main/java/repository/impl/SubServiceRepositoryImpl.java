package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import domain.other.Comments;
import domain.serviceEntity.SubService;
import repository.CommentsRepository;
import repository.SubServiceRepository;

import javax.persistence.EntityManager;
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

          /*   if (subService !=null){
                 subService.setDescription(newDescription);
                 subService.setPrice(newPrice);

                 commitTransaction();
             }*/

            if (subService != null) {
                entityManager.createQuery("UPDATE SubService s SET s.description = :newDescription " +
                                " WHERE s.id =:installmentIds ")
                        .setParameter("newDescription", newDescription)
                        .executeUpdate();

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
     /*   try {
            beginTransaction();
            SubService subService = entityManager.find(SubService.class, subServiceId);

            if (subService != null) {
                entityManager.createQuery("UPDATE SubService s SET s.price = :price " +
                                " WHERE s.id IN :installmentIds ")
                        .setParameter("price", price)
                        .executeUpdate();

                commitTransaction();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return false;*/

        try {
            beginTransaction();
            List<Integer> subServiceId1 = entityManager.createQuery("SELECT s.id FROM SubService s WHERE s.id = :subServiceId ", Integer.class)
                    .setParameter("subServiceId", subServiceId)
                    .getResultList();

            if (subServiceId1 != null && !subServiceId1.isEmpty()) {
                entityManager.createQuery("UPDATE SubService s SET " +
                                "s.price = :price WHERE s.id IN :subServiceId1")
                        .setParameter("price", price)
                        .setParameter("subServiceId1", subServiceId1)
                        .executeUpdate();

                commitTransaction();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
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
    }
}