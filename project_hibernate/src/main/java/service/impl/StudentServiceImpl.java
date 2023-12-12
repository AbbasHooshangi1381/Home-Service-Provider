package service.impl;

import base.service.impl.BaseEntityServiceImpl;
import entity.Student;
import repository.StudentRepository;
import service.StudentService;

import java.util.Date;

public class StudentServiceImpl extends BaseEntityServiceImpl<Integer,Student, StudentRepository>
        implements StudentService {
    public StudentServiceImpl(StudentRepository baseRepository) {
        super(baseRepository);
    }


/*    @Override
    public void addEducationLoanToCard(Integer id) {
        baseRepository.addEducationLoanToCard(id);
    }

    @Override
    public Boolean isLoanDateEligible(Date lastLoanDate) {
        return baseRepository.isLoanDateEligible(lastLoanDate);
    }

    @Override
    public void addEducationLoanToCardCollumn2(Integer id) {
    baseRepository.addEducationLoanToCardCollumn2(id);
    }

    @Override
    public void addEducationLoanToCardCollumn3(Integer id) {
    baseRepository.addEducationLoanToCardCollumn3(id);
    }

    @Override
    public void addHousingLoanOfStudentCollumn1(Integer id, Integer term, String city) {
    baseRepository.addHousingLoanOfStudentCollumn1(id,term,city);
    }*/
}
