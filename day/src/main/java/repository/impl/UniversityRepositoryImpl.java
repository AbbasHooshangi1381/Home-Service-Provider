package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import entity.University;
import repository.UniversityRepository;

import javax.persistence.EntityManager;

public class UniversityRepositoryImpl extends BaseEntityRepositoryImpl<Integer, University> implements UniversityRepository {
    public UniversityRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<University> getEntityClass() {
        return University.class;
    }
}
