package service.impl;

import base.service.impl.BaseEntityServiceImpl;
import entity.University;
import repository.UniversityRepository;
import service.UniversityService;

public class UniversityServiceImpl extends BaseEntityServiceImpl<Integer, University, UniversityRepository> implements UniversityService {
    public UniversityServiceImpl(UniversityRepository baseRepository) {
        super(baseRepository);
    }
}
