package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import entity.Married;
import repository.MarriedRepository;

import javax.persistence.EntityManager;

public class MarriedRepositoryImpl extends BaseEntityRepositoryImpl<Integer, Married> implements MarriedRepository {
    public MarriedRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Married> getEntityClass() {
        return Married.class;
    }
}
