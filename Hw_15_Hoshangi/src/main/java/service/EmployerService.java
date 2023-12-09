package service;

import base.service.BaseService;
import model.Employer;
import model.Student;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface EmployerService extends BaseService<Employer, Integer> {

    Integer salary(Integer id);


    @Override
    Employer saveOrUpdate(Employer entity);

    @Override
    Optional<Employer> findById(Integer integer);

    @Override
    void deleteById(Integer integer);

    @Override
    Collection<Employer> findAll();

    @Override
    Employer login(String userName, String password);
}
