package repository;

import base.repository.BaseEntityRepository;
import entity.Student;

public interface StudentRepository extends BaseEntityRepository<Integer, Student> {

    Boolean canUseLoan(Integer id , Integer term );

}
