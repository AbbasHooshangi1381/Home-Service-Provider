package service.impl;

import base.service.BaseServiceImpl;
import model.Teacher;
import model.User;
import repository.TeacherRepository;
import repository.UserRepository;
import service.TeacherService;
import service.UserService;

public class TeacherServiceImpl extends BaseServiceImpl<Teacher,Integer , TeacherRepository> implements TeacherService {

    public TeacherServiceImpl(TeacherRepository repository) {
        super(repository);
    }


}
