package util;

import repository.*;
import repository.impl.*;
import service.*;
import service.impl.*;

import javax.persistence.EntityManager;

public class ApplicationContext {
    private final static EntityManager entityManager;
    private static final AdminRepository adminRepository;
    private static final AdminService adminService;
    private static final CustomerRepository customerRepository;
    private static final CustomerService customerService;
    private static final ExpertRepository expertRepository;
    private static final ExpertService expertService;
    private static final ServiceRepository serviceRepository;
    private static final ServiceService serviceService;
    private static final SubServiceRepository subServiceRepository;
    private static final SubServiceService subServiceService;


    static {
        entityManager = EntityManagerFactoryProvider.getEntityManagerFactory().createEntityManager();
        adminRepository = new AdminRepositoryImpl(entityManager);
        adminService = new AdminServiceImpl(adminRepository);
        customerRepository = new CustomerRepositoryImpl(entityManager);
        customerService = new CustomerServiceImpl(customerRepository);
        expertRepository = new ExpertRepositoryImpl(entityManager);
        expertService = new ExpertServiceImpl(expertRepository);
        serviceRepository=new ServiceRepositoryImpl(entityManager);
        serviceService=new ServiceServiceImpl(serviceRepository);
        subServiceRepository=new SubServiceRepositoryImpl(entityManager);
        subServiceService=new SubServiceServiceImpl(subServiceRepository);


    }

    public static AdminService getAdminService() {
        return adminService;
    }

    public static CustomerService getCustomerService() {
        return customerService;
    }

    public static ExpertService getExpertService() {
        return expertService;
    }

    public static ServiceService getServiceService() {
        return serviceService;
    }

    public static SubServiceService getSubServiceService() {
        return subServiceService;
    }


}
