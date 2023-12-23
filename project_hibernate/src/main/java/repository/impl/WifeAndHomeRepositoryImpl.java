package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import entity.Installment;
import entity.WifeAndHome;
import repository.InstallmentRepository;
import repository.WifeAndHomeRepository;

import javax.persistence.EntityManager;

public class WifeAndHomeRepositoryImpl  extends BaseEntityRepositoryImpl<Integer, WifeAndHome> implements WifeAndHomeRepository {
    public WifeAndHomeRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<WifeAndHome> getEntityClass() {
        return WifeAndHome.class;
    }
}
