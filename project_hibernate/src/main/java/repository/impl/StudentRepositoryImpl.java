package repository.impl;

import base.repository.impl.BaseEntityRepositoryImpl;
import entity.Card;
import entity.Student;
import entity.enumuration.City;
import entity.enumuration.SectionOfStudy;
import repository.StudentRepository;

import javax.persistence.EntityManager;
import java.util.Calendar;
import java.util.Date;

import static entity.enumuration.City.*;


public class StudentRepositoryImpl extends BaseEntityRepositoryImpl<Integer, Student> implements StudentRepository {
    public StudentRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Student> getEntityClass() {
        return Student.class;
    }
}

/*    @Override
    public Boolean canUseEducationLoan(Integer id) {
        Student student = entityManager.find(Student.class, id);
        if (student != null &&
                (student.getSectionOfStudy() == SectionOfStudy.ASSOCIATE_DEGREE ||
                        student.getSectionOfStudy() == SectionOfStudy.MASTERS) &&
                (student.getLastLoanDate() == null || isLoanDateEligible(student.getLastLoanDate()))) {
            return true;
        } else {
            return false;
        }
    }*/
/*    @Override
    public Boolean isLoanDateEligible(Date lastLoanDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(lastLoanDate);
        calendar.add(Calendar.MONTH, 6);
        Date nextEligibleDate = calendar.getTime();
        Date currentDate = new Date();

        return currentDate.compareTo(nextEligibleDate) >= 0;
    }

    @Override
    public void addEducationLoanToCard(Integer id) {
        Student student = entityManager.find(Student.class, id);
        if (student != null &&
                (student.getSectionOfStudy() == SectionOfStudy.ASSOCIATE_DEGREE ||
                        student.getSectionOfStudy() == SectionOfStudy.MASTERS) &&
                (student.getLastLoanDate() == null || isLoanDateEligible(student.getLastLoanDate()))) {
            Card card = new Card();
            card.setStudent(student);
            card.setAmountOfCard(1900000);
            student.setLastLoanDate(new Date());
            entityManager.persist(card);
        }
        System.out.println("The amount of loan is added to your card.");
    }


    //student of master, professional doctorate, continuous doctorate


    @Override
    public void addEducationLoanToCardCollumn2(Integer id) {
        Student student = entityManager.find(Student.class, id);
        if (student != null &&
                (student.getSectionOfStudy() == SectionOfStudy.PROFESSIONAL_DOCTOR ||
                        student.getSectionOfStudy() == SectionOfStudy.CONTINUOUS_PHD) &&
                (student.getLastLoanDate() == null || isLoanDateEligible(student.getLastLoanDate()))) {
            Card card = new Card();
            card.setStudent(student);
            card.setAmountOfCard(22500000);
            student.setLastLoanDate(new Date());
            entityManager.persist(card);
        }
        System.out.println("The amount of loan is added to your card.");
    }


    @Override
    public void addEducationLoanToCardCollumn3(Integer id) {
        Student student = entityManager.find(Student.class, id);
        if (student != null &&
                (student.getSectionOfStudy() == SectionOfStudy.UNCONTINUOUS_PHD) &&
                (student.getLastLoanDate() == null || isLoanDateEligible(student.getLastLoanDate()))) {
            Card card = new Card();
            card.setStudent(student);
            card.setAmountOfCard(2600000);
            student.setLastLoanDate(new Date());
            entityManager.persist(card);
        }
        System.out.println("The amount of loan is added to your card.");
    }

    @Override
    public void addHousingLoanOfStudentCollumn1(Integer id, Integer term, String city) {
        Student student = entityManager.find(Student.class, id);
        if (student != null && !student.getTerm().equals(term) && student.getCity().equals(checkCityType1(city))) {
            Card card = new Card();
            card.setStudent(student);
            card.setAmountOfCard(32000000);
            student.setLastLoanDate(new Date());
            entityManager.persist(card);
            System.out.println("The amount of loan is added to your card.");
        } else if (student != null && !student.getTerm().equals(term)) {
            checkCityType2(city);
            {
                Card card = new Card();
                card.setStudent(student);
                card.setAmountOfCard(26000000);
                student.setLastLoanDate(new Date());
                entityManager.persist(card);
                System.out.println("The amount of loan is added to your card.");
            }
        } else {
            if (!student.getTerm().equals(term)) {
                Card card = new Card();
                card.setStudent(student);
                card.setAmountOfCard(19500000);
                student.setLastLoanDate(new Date());
                entityManager.persist(card);
                System.out.println("The amount of loan is added to your card.");
            }
        }
    }
    public City[] checkCityType1(String city) {
        return new City[]{ GILAN, ISFAHAN, AZERBAIJAN_EAST, FARS, KHUZESTAN, QOM, KHORASAN_RAZAVI, ALBORZ };
    }

    public City[] checkCityType2(String city) {
        return new City[]{ TEHRAN };
    }

    }*/


