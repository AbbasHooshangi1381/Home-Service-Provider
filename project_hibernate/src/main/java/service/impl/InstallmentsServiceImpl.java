package service.impl;

import base.service.impl.BaseEntityServiceImpl;
import entity.Installment;
import entity.Loan;
import repository.InstallmentRepository;
import repository.LoanRepository;
import service.InstallmentsService;
import service.LoanService;

public class InstallmentsServiceImpl extends BaseEntityServiceImpl<Integer, Installment, InstallmentRepository>
        implements InstallmentsService {
    public InstallmentsServiceImpl(InstallmentRepository baseRepository) {
        super(baseRepository);
    }

    @Override
    public double installment(Integer id, Integer year) {
        return baseRepository.installment(id,year);
    }
}
