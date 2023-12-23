package service;

import base.service.BaseEntityService;
import entity.Installment;
import entity.Loan;
import entity.Student;

import java.time.LocalDate;
import java.util.List;

public interface InstallmentsService extends BaseEntityService<Integer, Installment> {

    List<Installment> showInstallments(Integer id);

    List<Installment> unpaidInstallments(Integer id);

    Boolean payInstallments(Integer payNumber,Integer studentId);

    List<Installment> paidInstallments(Integer studentId);
}
