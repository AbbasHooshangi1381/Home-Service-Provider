package repository;

import base.repository.BaseEntityRepository;
import entity.Installment;

import java.util.List;

public interface InstallmentRepository extends BaseEntityRepository<Integer, Installment> {

     void showInstallments(Integer id);

    void graduatedStudent(Integer id);

    List<Installment>unpaidInstallments(Integer id);
}
