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
    public void showInstallments(Integer id) {
         baseRepository.showInstallments(id);
    }

    @Override
    public void graduatedStudent(Integer id) {
        baseRepository.graduatedStudent(id);
    }
}
