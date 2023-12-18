package service.impl;

import base.service.impl.BaseEntityServiceImpl;
import entity.Installment;
import entity.Loan;
import repository.InstallmentRepository;
import repository.LoanRepository;
import service.InstallmentsService;
import service.LoanService;

import java.util.List;

public class InstallmentsServiceImpl extends BaseEntityServiceImpl<Integer, Installment, InstallmentRepository>
        implements InstallmentsService {
    public InstallmentsServiceImpl(InstallmentRepository baseRepository) {
        super(baseRepository);
    }


    @Override
    public List<Installment> showInstallments(Integer id) {
        return baseRepository.showInstallments(id);
    }

    @Override
    public String graduatedStudent(Integer id) {
        return baseRepository.graduatedStudent(id);
    }

    @Override
    public List<Installment> unpaidInstallments(Integer payNumber, Integer studentId) {
        return baseRepository.unpaidInstallments(payNumber,studentId);
    }

    @Override
    public Boolean payInstallments(Integer payNumber, Integer studentId) {
        return baseRepository.payInstallments(payNumber,studentId);
    }

    @Override
    public List<Installment> paidInstallments(Integer payNumber, Integer studentId) {
        return baseRepository.paidInstallments(payNumber,studentId);
    }
}
