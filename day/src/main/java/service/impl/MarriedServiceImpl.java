package service.impl;

import base.service.impl.BaseEntityServiceImpl;
import entity.Married;
import repository.MarriedRepository;
import service.MarriedService;

public class MarriedServiceImpl extends BaseEntityServiceImpl<Integer, Married, MarriedRepository> implements MarriedService {
    public MarriedServiceImpl(MarriedRepository baseRepository) {
        super(baseRepository);
    }
}
