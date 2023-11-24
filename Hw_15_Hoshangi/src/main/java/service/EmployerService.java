package service;

import base.service.BaseService;
import model.Employer;
import model.Student;

import java.util.List;

public interface EmployerService extends BaseService<Employer, Integer> {

    Integer salary(Integer id);

}
