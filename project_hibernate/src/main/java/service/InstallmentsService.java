package service;

import base.service.BaseEntityService;
import entity.Installment;
import entity.Loan;

public interface InstallmentsService extends BaseEntityService<Integer, Installment> {
    void showInstallments(Integer id );
     void graduatedStudent(Integer id);
}
