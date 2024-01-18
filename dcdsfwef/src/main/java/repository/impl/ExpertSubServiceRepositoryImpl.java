package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import domain.other.ExpertSubService;
import domain.userEntity.Expert;
import repository.ExpertRepository;
import repository.ExpertSubServiceRepository;

import javax.persistence.EntityManager;

public class ExpertSubServiceRepositoryImpl extends BaseEntityRepositoryImpl<Integer, ExpertSubService>
        implements ExpertSubServiceRepository {
    public ExpertSubServiceRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<ExpertSubService> getEntityClass() {
        return ExpertSubService.class;
    }
}
