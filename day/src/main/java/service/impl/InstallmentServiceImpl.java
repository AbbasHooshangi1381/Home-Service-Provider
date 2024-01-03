package service.impl;

import base.service.impl.BaseEntityServiceImpl;
import entity.Installment;
import entity.Loan;
import entity.Student;
import entity.enumuration.InstallmentStatus;
import repository.InstallmentRepository;
import service.InstallmentService;

import java.util.List;
import java.util.Optional;

public class InstallmentServiceImpl extends BaseEntityServiceImpl<Integer, Installment, InstallmentRepository>implements
        InstallmentService {
    public InstallmentServiceImpl(InstallmentRepository baseRepository) {
        super(baseRepository);
    }

    @Override
    public List<Installment> paidInstallment(Student student, Loan loan, InstallmentStatus status) {
        return baseRepository.paidInstallment(student,loan,status);
    }

    @Override
    public List<Installment> unPaidInstallment(Student student, Loan loan, InstallmentStatus status) {
        return baseRepository.unPaidInstallment(student,loan,status);
    }

    @Override
    public Optional<Installment> findByStudentId(Integer id, Student student) {
        return baseRepository.findByStudentId(id, student);
    }


}
