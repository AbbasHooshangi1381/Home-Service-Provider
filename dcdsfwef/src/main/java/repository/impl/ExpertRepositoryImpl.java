package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import domain.enumurations.ExpertStatus;
import domain.serviceEntity.SubService;
import domain.userEntity.Expert;
import repository.ExpertRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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

         /*   List<Integer> idOfExpert = entityManager.createQuery("SELECT e.id FROM Expert e WHERE e.id = :id", Integer.class)
                    .setParameter("id", id)
                    .getResultList();

            if (idOfExpert != null) {
                entityManager.createQuery("UPDATE Expert e " +
                                "SET e.subServiceList.service.id = :serviceId, " +
                                "e.subServiceList.id = :subServiceId " +
                                "WHERE e.id = :expertId")
                        .setParameter("serviceId", 2)
                        .setParameter("subServiceId", 2)
                        .setParameter("expertId", idOfExpert)
                        .executeUpdate();*/
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
            List<Integer> expertIds = entityManager.createQuery("SELECT e.id FROM Expert e " +
                            "WHERE e.id = :id", Integer.class)
                    .setParameter("id", id)
                    .getResultList();

            if (expertIds != null) {
                entityManager.createQuery("UPDATE Expert e SET e.expertStatus = :completePaid WHERE e.id IN :expertIds")
                        .setParameter("completePaid", ExpertStatus.CONFIRMED)
                        .setParameter("expertIds", expertIds)
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
    public Boolean changePassword(Integer id, String newPassword) {
        try {
            beginTransaction();
            List<String> idOfCustomer = entityManager.createQuery("SELECT e.password FROM Expert e " +
                            "WHERE e.id = :id", String.class)
                    .setParameter("id", id)
                    .getResultList();

            if (idOfCustomer != null) {
                entityManager.createQuery("UPDATE Expert e " +
                                "SET e.password = :password " +
                                "WHERE e.id =: id")
                        .setParameter("id", id)
                        .executeUpdate();

                commitTransaction();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

