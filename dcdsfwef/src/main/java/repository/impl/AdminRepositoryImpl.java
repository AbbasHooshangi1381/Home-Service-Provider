package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import domain.userEntity.Admin;
import domain.userEntity.Expert;
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

            Admin admin = entityManager.find(Admin.class, id);
            if (admin != null) {
                admin.setPassword(newPassword);
                entityManager.merge(admin);

                commitTransaction();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}