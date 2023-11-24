package service.impl;

import base.service.BaseServiceImpl;
import model.Employer;
import model.Student;
import repository.EmployerRepository;
import repository.StudentRepository;
import repository.impl.EmployerRepositoryImpl;
import repository.impl.StudentRepositoryImpl;
import service.EmployerService;

import java.util.List;

public class EmployerServiceImpl extends BaseServiceImpl<Employer,Integer , EmployerRepository> implements EmployerService {

    EmployerRepositoryImpl employerRepository;

    public EmployerServiceImpl(EmployerRepository repository) {
        super(repository);
    }


    @Override
    public Integer salary(Integer id) {
        return employerRepository.salary(id);
    }


}
