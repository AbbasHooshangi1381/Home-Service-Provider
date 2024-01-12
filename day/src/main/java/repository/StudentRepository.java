package repository;

import base.repository.BaseEntityRepository;
import base.repository.impl.BaseEntityRepositoryImpl;
import entity.Student;

import javax.persistence.EntityManager;
import java.util.Optional;

public interface StudentRepository extends BaseEntityRepository<Integer, Student> {

    boolean existByUserName(String userName);

    Optional<Student> showUsernameAndPasswordForStudentNextSignup(String username);

    boolean existByNationalCode(String nationalCode);

    Optional<Student> login(String username,String password);


}
