package service;

import base.service.BaseEntityService;
import entity.Installment;
import entity.Loan;

import java.util.List;

public interface InstallmentsService extends BaseEntityService<Integer, Installment> {

    List<Installment> showInstallments(Integer id);

    String graduatedStudent(Integer id);

    List<Installment> unpaidInstallments(Integer payNumber,Integer studentId);

    Boolean payInstallments(Integer payNumber,Integer studentId);

    List<Installment> paidInstallments(Integer payNumber,Integer studentId);
}
