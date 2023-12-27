package repository;

import base.repository.BaseEntityRepository;
import entity.Installment;
import entity.Student;

import java.time.LocalDate;
import java.util.List;

public interface InstallmentRepository extends BaseEntityRepository<Integer, Installment> {

    List<Installment> showInstallments(Integer id);

    List<Installment>  unpaidInstallments(Integer id);

    Boolean payInstallments(Integer payNumber, Integer studentId);

    List<Object[]> paidInstallments(Integer id);
}
