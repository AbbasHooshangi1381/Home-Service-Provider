package repository.impl;

import base.repository.BaseRepositoryImpl;
import lombok.NoArgsConstructor;
import model.Employer;
import org.hibernate.Session;
import repository.EmployerRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
@NoArgsConstructor
public class EmployerRepositoryImpl extends BaseRepositoryImpl<Employer , Integer> implements EmployerRepository {


    EntityManager entityManager;

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
    public Employer login(String userName, String password) {
        return super.login(userName, password);
    }

    @Override
    public Integer salary(Integer id) {
        TypedQuery<Integer> query=
                entityManager.createQuery("SELECT e.salary FROM Employer e WHERE e.id = :id", Integer.class);

        query.setParameter("id",id);
        Integer count=query.getSingleResult();
        return count;
    }
}
