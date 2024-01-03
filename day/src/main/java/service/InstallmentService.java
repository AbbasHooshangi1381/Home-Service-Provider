package service;

import base.service.BaseEntityService;
import entity.Installment;
import entity.Loan;
import entity.Student;
import entity.enumuration.InstallmentStatus;

import java.util.List;
import java.util.Optional;

public interface InstallmentService extends BaseEntityService<Integer, Installment> {

    List<Installment> paidInstallment(Student student, Loan loan, InstallmentStatus status);

    List<Installment> unPaidInstallment(Student student, Loan loan, InstallmentStatus status);

    Optional<Installment> findByStudentId(Integer id , Student student);

}
