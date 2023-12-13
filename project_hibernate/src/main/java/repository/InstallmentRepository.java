package repository;

import base.repository.BaseEntityRepository;
import entity.Installment;

public interface InstallmentRepository extends BaseEntityRepository<Integer, Installment> {

    double installment(Integer id, Integer year);

}
