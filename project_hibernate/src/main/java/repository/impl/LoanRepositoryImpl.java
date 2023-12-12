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

import static entity.enumuration.City.*;

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
    public void addHousingLoanOfStudent(Integer id, String city) {
        Student student = entityManager.find(Student.class, id);

        if (student != null  && student.getCity().equals(checkCityType1(city))
        && !student.getGettingLoan() && !student.getHavingDorm() &&
                student.getMarriedOrSingle()==MarriedOrSingle.MARRIED) {
            Card card = new Card();
            card.setStudent(student);
            card.setAmountOfCard(32000000);
            student.setLastLoanDate(new Date());
            entityManager.persist(card);
            System.out.println("The amount of loan is added to your card.");


        } else if (student != null && student.getCity().equals(checkCityType2(city))){

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


    public City[] checkCityType1(String city) {
        return new City[]{ GILAN, ISFAHAN, AZERBAIJAN_EAST, FARS, KHUZESTAN, QOM, KHORASAN_RAZAVI, ALBORZ };
    }

    public City[] checkCityType2(String city) {
        return new City[]{ TEHRAN };
    }

    ////////////////////////////////////////////////////



    @Override
    public void addPaymentOfUniversity(Integer id) {
        Student student = entityManager.find(Student.class, id);
        if (student != null && student.getUniversityType() == UniversityType.Non_Governmental_University &&
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


