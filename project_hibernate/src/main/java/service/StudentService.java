package service;

import base.service.BaseEntityService;
import entity.Student;

public interface StudentService extends BaseEntityService<Integer, Student> {

    Boolean canUseLoan(Integer id , Integer term );

}
