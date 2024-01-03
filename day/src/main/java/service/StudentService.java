package service;

import base.service.BaseEntityService;
import entity.Student;

import java.util.Optional;

public interface StudentService extends BaseEntityService<Integer , Student> {

    boolean existByUserName(String userName);

    Optional<Student> showUsernameAndPasswordForStudentNextSignup(String username);

    boolean existByNationalCode(String nationalCode);

}
