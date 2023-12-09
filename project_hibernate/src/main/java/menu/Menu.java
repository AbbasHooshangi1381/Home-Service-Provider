package menu;

import entity.Student;
import entity.enumuration.DayOrNight;
import entity.enumuration.MarriedOrSingle;
import entity.enumuration.SectionOfStudy;
import entity.enumuration.UniversityType;
import lombok.ToString;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@ToString
public class Menu {
    public static Optional<Student> optionalStudent;

    public static final Scanner scanner = new Scanner(System.in);

    public void fistMenu() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n*************************************************************");
        System.out.println("|                WELCOME TO HIBERNATE-PROJECT                 |");
        System.out.println("*************************************************************\n");
        System.out.println("1- Sign in");
        System.out.println("2- Sign up");
        System.out.println("3- Exit\n");
        System.out.println("Enter your select:");
        try {
            int select = scanner.nextInt();
            scanner.nextLine();
            switch (select) {
                case 1 -> login();
                case 2 -> signup();
                case 3 -> {
                    System.out.println("exit");
                    break;
                }
                default -> System.out.println("---Eror404---");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("!!!WRONG!!!");
        }


    }


    public static void signup() {
        Student student = new Student();

        System.out.println("Enter firstName :  ");
        String firstName = scanner.next();
        student.setFirstName(firstName);

        System.out.println("Enter lastName : ");
        String lastName = scanner.next();
        student.setLastName(lastName);

        System.out.println("Enter motherName :");
        String motherName = scanner.nextLine();
        student.setMotherName(motherName);

        System.out.println("Enter shenasname number : ");
        String shenasname = scanner.next();
        student.setBirthCertificateNumber(shenasname);

        System.out.println("Enter nationalCode : ");
        String nationalCode = scanner.nextLine();
        student.setNationalCode(nationalCode);

        System.out.println("Enter DOB : ");
        LocalDate DOB = LocalDate.parse(scanner.next());
        student.setDateOfBirth(DOB);

        System.out.println("Enter name of university :");
        String nameOfUniversity = scanner.next();
        student.setUniversityName(nameOfUniversity);

        System.out.println("Enter enter year :");
        String enterYear = scanner.nextLine();
        student.setEnterYear(enterYear);

        System.out.println("Enter number of term :");
        int numberOfTerm = scanner.nextInt();
        student.setTerm(numberOfTerm);

        System.out.println("Are you getting loan until now?");
        boolean gettingLoan = scanner.nextBoolean();
        student.setGettingLoan(gettingLoan);

        System.out.println("Are you getting Dorm?");
        boolean havingDorm = scanner.nextBoolean();
        student.setHavingDorm(havingDorm);

        System.out.println("choose university type: \n 1.State_University \n 2.Non_Governmental_University");
        int typeOfUniversity = scanner.nextInt();
        switch (typeOfUniversity) {
            case 1 -> student.setUniversityType(UniversityType.State_University);
            case 2 -> student.setUniversityType(UniversityType.Non_Governmental_University);
        }


        System.out.println("choose section of study : \n 1.ASSOCIATE_DEGREE \n 2.MASTERS \n 3.PROFESSIONAL_DOCTOR" +
                " \n 4.CONTINUOUS_PHD \n 5.UNCONTINUOUS_PHD");
        int sectionOfStudy = scanner.nextInt();
        switch (sectionOfStudy) {
            case 1 -> student.setSectionOfStudy(SectionOfStudy.ASSOCIATE_DEGREE);
            case 2 -> student.setSectionOfStudy(SectionOfStudy.MASTERS);
            case 3 -> student.setSectionOfStudy(SectionOfStudy.PROFESSIONAL_DOCTOR);
            case 4 -> student.setSectionOfStudy(SectionOfStudy.CONTINUOUS_PHD);
            case 5 -> student.setSectionOfStudy(SectionOfStudy.UNCONTINUOUS_PHD);

        }

        System.out.println("Are you day student or night one?: \n 1.day \n 2.night");
        int dayOrNight = scanner.nextInt();
        switch (dayOrNight) {
            case 1 -> student.setDayOrNight(DayOrNight.DAY);
            case 2 -> student.setDayOrNight(DayOrNight.NIGHT);
        }

        System.out.println("Are you married or single ?: \n 1.married \n 2.single");
        int marriedOrSingle = scanner.nextInt();
        switch (marriedOrSingle) {
            case 1 -> student.setMarriedOrSingle(MarriedOrSingle.MARRIED);
            case 2 -> student.setMarriedOrSingle(MarriedOrSingle.SINGLE);
        }


        // ApplicationContext.getStudentServiceImpl().save(student);
        System.out.println("ADD to DataBase SUCESSFULLY");

        String password = generatePassword();
        student.setPassword(password);

        System.out.println("Username: " + student.getNationalCode());
        System.out.println("Password: " + student.getPassword());
    }

    public static String generatePassword() {
        Random random = new Random();
        String password = random.ints(8, 'a', 'z' + 1)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return password;
    }



    public static void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the current time (yyyy-MM-dd): ");
        String inputTime = scanner.nextLine();

        LocalDate currentTime = LocalDate.parse(inputTime, DateTimeFormatter.ISO_DATE);

        LocalDate firstStartDate = LocalDate.parse("yyyy-MM-dd", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate firstEndDate = firstStartDate.plusWeeks(1);

        LocalDate secondStartDate = LocalDate.parse("yyyy-MM-dd", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate secondEndDate = secondStartDate.plusWeeks(1);

        if (currentTime.isAfter(firstStartDate) && currentTime.isBefore(firstEndDate)) {
            //       System.out.println("User can login to the system - First registration window.");
        } else if (currentTime.isAfter(secondStartDate) && currentTime.isBefore(secondEndDate)) {
            //       System.out.println("User can login to the system - Second registration window.");
        } else {
            System.out.println("User is outside the allowed time periods.");
        }
    }
}