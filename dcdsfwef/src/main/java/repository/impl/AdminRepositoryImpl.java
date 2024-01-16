package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import domain.userEntity.Admin;
import repository.AdminRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class AdminRepositoryImpl extends BaseEntityRepositoryImpl<Integer, Admin> implements AdminRepository {
    public AdminRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Admin> getEntityClass() {
        return Admin.class;
    }

    @Override
    public List<String> showEmail() {

        TypedQuery<String> query = entityManager.createQuery("SELECT a.email FROM Admin a", String.class);
        return query.getResultList();
    }

    @Override
    public Boolean changePassword(Integer id, String newPassword) {
            try {
                beginTransaction();
                final List<String> idOfAdmin = entityManager.createQuery("SELECT a.password FROM Admin a " +
                                "WHERE a.id = :id", String.class)
                        .setParameter("id", id)
                        .getResultList();

                if (idOfAdmin != null) {
                    entityManager.createQuery("UPDATE Admin a " +
                                    "SET a.password = :password "+
                                    "WHERE a.id =: id")
                            .setParameter("id", id)
                            .executeUpdate();

                    commitTransaction();
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;    }
    }