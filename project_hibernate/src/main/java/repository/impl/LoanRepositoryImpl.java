package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import entity.Card;
import entity.Loan;
import entity.Student;
import entity.enumuration.City;
import entity.enumuration.MarriedOrSingle;
import entity.enumuration.SectionOfStudy;
import entity.enumuration.UniversityType;
import repository.LoanRepository;
import repository.StudentRepository;

import javax.persistence.EntityManager;
import java.util.Calendar;
import java.util.Date;
import java.util.EnumSet;

import static entity.enumuration.City.*;
import static entity.enumuration.UniversityType.*;

public class LoanRepositoryImpl extends BaseEntityRepositoryImpl<Integer, Loan> implements LoanRepository {
    public LoanRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Loan> getEntityClass() {
        return Loan.class;
    }

    @Override
    public void addEducationLoanToCard(Integer id) {
        Student student = entityManager.find(Student.class, id);
        if (student != null && isLoanDateEligible(student.getLastLoanDate())) {
            Card card = new Card();
            card.setStudent(student);

            SectionOfStudy sectionOfStudy = student.getSectionOfStudy();
            int amount = 0;

            if (sectionOfStudy == SectionOfStudy.ASSOCIATE_DEGREE || sectionOfStudy == SectionOfStudy.MASTERS) {
                amount = 1900000;
            } else if (sectionOfStudy == SectionOfStudy.PROFESSIONAL_DOCTOR || sectionOfStudy == SectionOfStudy.CONTINUOUS_PHD) {
                amount = 22500000;
            } else if (sectionOfStudy == SectionOfStudy.UNCONTINUOUS_PHD) {
                amount = 2600000;
            }

            if (amount != 0) {
                card.setAmountOfCard(amount);
                student.setLastLoanDate(new Date());
                entityManager.persist(card);

                System.out.println("The amount of loan is added to your card.");
                return;
            }
        }

        System.out.println("Failed to add the amount of loan to your card.");
    }



    @Override
    public Boolean isLoanDateEligible(Date lastLoanDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(lastLoanDate);
        calendar.add(Calendar.MONTH, 6);
        Date nextEligibleDate = calendar.getTime();
        Date currentDate = new Date();

        return currentDate.compareTo(nextEligibleDate) >= 0;
    }

    ////////////////////////////////////////////////////

    @Override
    public void addHousingLoanOfStudent(Integer id, City city) {
        Student student = entityManager.find(Student.class, id);

        if (student != null && checkCityType1(city) && !student.getGettingLoan() &&
                !student.getHavingDorm() && student.getMarriedOrSingle() == MarriedOrSingle.MARRIED) {

            Card card = new Card();
            card.setStudent(student);
            card.setAmountOfCard(32000000);
            student.setLastLoanDate(new Date());
            entityManager.persist(card);
            System.out.println("The amount of loan is added to your card.");

        } else if (student != null && checkCityType2(city)) {

            Card card = new Card();
            card.setStudent(student);
            card.setAmountOfCard(26000000);
            student.setLastLoanDate(new Date());
            entityManager.persist(card);
            System.out.println("The amount of loan is added to your card.");

        } else {

            Card card = new Card();
            card.setStudent(student);
            card.setAmountOfCard(19500000);
            assert student != null;
            student.setLastLoanDate(new Date());
            entityManager.persist(card);
            System.out.println("The amount of loan is added to your card.");

        }
    }

    public boolean checkCityType1(City city) {
        return EnumSet.of(City.GILAN, City.ISFAHAN, City.AZERBAIJAN_EAST, City.FARS, City.KHUZESTAN,
                City.QOM, City.KHORASAN_RAZAVI, City.ALBORZ).contains(city);
    }

    public boolean checkCityType2(City city) {
        return city == City.TEHRAN;
    }

    ////////////////////////////////////////////////////



    @Override
    public void addPaymentOfUniversity(Integer id, UniversityType universityType) {
        Student student = entityManager.find(Student.class, id);
        if (student != null && student.getUniversityType().equals(universityType) &&
                (student.getLastLoanDate() == null || isLoanDateEligible(student.getLastLoanDate()))) {
            Card card = new Card();
            card.setStudent(student);

            SectionOfStudy sectionOfStudy = student.getSectionOfStudy();
            int amount = 0;

            if (sectionOfStudy == SectionOfStudy.ASSOCIATE_DEGREE || sectionOfStudy == SectionOfStudy.MASTERS) {
                amount = 1900000;
            } else if (sectionOfStudy == SectionOfStudy.PROFESSIONAL_DOCTOR || sectionOfStudy == SectionOfStudy.CONTINUOUS_PHD) {
                amount = 22500000;
            } else if (sectionOfStudy == SectionOfStudy.UNCONTINUOUS_PHD) {
                amount = 2600000;
            }

            if (amount != 0) {
                card.setAmountOfCard(amount);
                student.setLastLoanDate(new Date());
                entityManager.persist(card);

                System.out.println("The amount of loan is added to your card.");
                return;
            }
        }

        System.out.println("Failed to add the amount of loan to your card.");
    }



}


