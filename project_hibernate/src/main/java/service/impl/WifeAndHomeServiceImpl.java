package service.impl;

import base.service.impl.BaseEntityServiceImpl;
import entity.Student;
import entity.WifeAndHome;
import repository.StudentRepository;
import repository.WifeAndHomeRepository;
import service.StudentService;
import service.WifeAndHomeService;

public class WifeAndHomeServiceImpl extends BaseEntityServiceImpl<Integer, WifeAndHome, WifeAndHomeRepository>
        implements WifeAndHomeService {

    public WifeAndHomeServiceImpl(WifeAndHomeRepository baseRepository) {
        super(baseRepository);
    }
}
