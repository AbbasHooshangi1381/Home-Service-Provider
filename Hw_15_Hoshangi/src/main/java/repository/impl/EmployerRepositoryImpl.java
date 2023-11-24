package repository.impl;

import base.repository.BaseRepositoryImpl;
import model.Employer;
import repository.EmployerRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class EmployerRepositoryImpl extends BaseRepositoryImpl<Employer , Integer> implements EmployerRepository {

    public EmployerRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Employer> getEntityClass() {
        return Employer.class;
    }

    @Override
    public Employer saveOrUpdate(Employer entity) {
        return super.saveOrUpdate(entity);
    }

    @Override
    public void deleteById(Integer integer) {
        super.deleteById(integer);
    }


    @Override
    public Integer salary(Integer id) {
        TypedQuery<Integer>query=
                entityManager.createQuery("SELECT e.salary FROM Employer e WHERE e.id = :id", Integer.class);

        query.setParameter("id",id);
        Integer count=query.getSingleResult();
        return count;
    }
}
