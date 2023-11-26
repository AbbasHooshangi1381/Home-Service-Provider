package service;

import base.service.BaseService;
import model.Teacher;
import model.User;

import java.util.Optional;

public interface TeacherService extends BaseService<Teacher, Integer> {
    @Override
    Optional<Teacher> findById(Integer integer);

    Double calculateSalary(Integer term);

}
