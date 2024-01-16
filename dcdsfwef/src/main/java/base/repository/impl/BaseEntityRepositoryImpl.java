package base.repository.impl;

import base.domin.BaseEntity;
import base.repository.BaseEntityRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
public abstract class BaseEntityRepositoryImpl<ID extends Serializable, T extends BaseEntity<ID>> implements BaseEntityRepository<ID, T> {
    protected EntityManager entityManager;

    public BaseEntityRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public T save(T entity) {
        beginTransaction();
        entity = saveWithoutTransaction(entity);
        commitTransaction();
        return entity;
    }



    private T saveWithoutTransaction(T entity) {
        if (entity.getId() == null)
            entityManager.persist(entity);
        else
            entity = entityManager.merge(entity);
        return entity;
    }

    @Override
    public List<T> saveAll(List<T> entities) {
        beginTransaction();

        List<T> savedEntities = new ArrayList<>();

        for (T entity : entities) {
            T savedEntity = saveWithoutTransaction(entity);
            savedEntities.add(savedEntity);
        }
        return entities;
    }

    @Override
    public void deleteById(ID id) {
        beginTransaction();
        Optional<T> optional = findById(id);
        optional.ifPresent(entityManager::remove);
        commitTransaction();
    }

    @Override
    public Collection<T> findAll() {
        return entityManager.createQuery(
                "from " + getEntityClass().getSimpleName(), getEntityClass()).getResultList();
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(entityManager.find(getEntityClass(),id));
    }

    @Override
    public Optional<T> login(String userName, String password) {
        try {
            return Optional.ofNullable(entityManager.createQuery("SELECT u FROM "
                            + getEntityClass().getSimpleName()
                            + " u WHERE u.userName = :userName AND u.password = :password", getEntityClass())
                    .setParameter("userName", userName)
                    .setParameter("password", password)
                    .getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public abstract Class<T> getEntityClass();

    @Override
    public void beginTransaction() {
        if (!entityManager.getTransaction().isActive())
            entityManager.getTransaction().begin();
    }

    @Override
    public void commitTransaction() {
        if (entityManager.getTransaction().isActive())
            entityManager.getTransaction().commit();
    }

    @Override
    public void rollBack() {
        if (entityManager.getTransaction().isActive())
            entityManager.getTransaction().rollback();
    }

}
