package base.repository.impl;

import base.domin.BaseEntity;
import base.repository.BaseEntityRepository;


import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.io.Serializable;
import java.util.Collection;
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
    public Optional<T> login(String username, String password) {
        try {
            return Optional.ofNullable(entityManager.createQuery("SELECT u FROM "
                            + getEntityClass().getSimpleName()
                            + " u WHERE u.username = :username AND u.password = :password", getEntityClass())
                    .setParameter("username", username)
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
