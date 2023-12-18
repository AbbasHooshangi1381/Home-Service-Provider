package repository;

import base.repository.BaseEntityRepository;
import entity.Installment;

import java.util.List;

public interface InstallmentRepository extends BaseEntityRepository<Integer, Installment> {

    List<Installment> showInstallments(Integer id);

    String graduatedStudent(Integer id);

    List<Installment> unpaidInstallments(Integer payNumber, Integer studentId);

    Boolean payInstallments(Integer payNumber, Integer studentId);

    List<Installment> paidInstallments(Integer payNumber, Integer studentId);
}
