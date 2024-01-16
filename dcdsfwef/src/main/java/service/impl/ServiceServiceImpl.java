package service.impl;

import base.service.impl.BaseEntityServiceImpl;
import domain.serviceEntity.Service;
import repository.ServiceRepository;
import service.ServiceService;

public class ServiceServiceImpl extends BaseEntityServiceImpl<Integer, Service, ServiceRepository>
        implements ServiceService {
    public ServiceServiceImpl(ServiceRepository baseRepository) {
        super(baseRepository);
    }
}
