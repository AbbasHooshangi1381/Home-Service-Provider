package menu;

import entity.Card;
import entity.Student;
import entity.enumuration.*;
import lombok.ToString;
import util.ApplicationContext;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
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
                case 1 -> signIn();
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
        boolean isTrue = true;
        while (true) {
            Student student = new Student();
            Card card = new Card();

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

            System.out.println("Are you getting loan until now? (write just (true) or (false))");
            boolean gettingLoan = scanner.nextBoolean();
            student.setGettingLoan(gettingLoan);

            System.out.println("Are you getting Dorm?(write just (true) or (false))");
            boolean havingDorm = scanner.nextBoolean();
            student.setHavingDorm(havingDorm);

            System.out.println("Enter your City :");
            String city = scanner.nextLine();
            student.setCity(city);

            System.out.println("choose university type: \n 1.AZAD_UNIVERSITY \n 2.DAILY_GOVERMENT \n 3.NIGHTLY_GOVERMENT" +
                    "\n 4.EXCESS_CAPACITY \n 5.NON_PROFIT_UNIVERSITIES \n 6.ELMI_CARBORDI \n7.PAARDIS \n 8.PAYAM_NOOR ");
            int typeOfUniversity = scanner.nextInt();
            switch (typeOfUniversity) {
                case 1 -> student.setUniversityType(UniversityType.AZAD_UNIVERSITY);
                case 2 -> student.setUniversityType(UniversityType.DAILY_GOVERMENT);
                case 3 -> student.setUniversityType(UniversityType.NIGHTLY_GOVERMENT);
                case 4 -> student.setUniversityType(UniversityType.EXCESS_CAPACITY);
                case 5 -> student.setUniversityType(UniversityType.NON_PROFIT_UNIVERSITIES);
                case 6 -> student.setUniversityType(UniversityType.ELMI_CARBORDI);
                case 7 -> student.setUniversityType(UniversityType.PAARDIS);
                case 8 -> student.setUniversityType(UniversityType.PAYAM_NOOR);
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


            System.out.println("-------------------------------------------");
            System.out.println("1. register for loan :");
            System.out.println("2. refund :");//بازپرداخت
            System.out.println("show user panel : ");

            try {
                int select = scanner.nextInt();
                scanner.nextLine();
                switch (select) {
                    case 1 -> registerLoan();
                    case 2 ->
                    case 3 ->

                    default -> System.out.println("---Eror404---");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("!!!WRONG!!!");
            }

            System.out.println("Please enter The bank of your card :");
            String bank = scanner.nextLine().toUpperCase();
            scanner.nextLine();
            try {
                NameOfBank nameOfBank = NameOfBank.valueOf(bank);
                SaveCard();
            } catch (Exception e) {
                System.out.println("Invalid bank name");
            }

        }
    }

    public static String generatePassword() {
        Random random = new Random();
        String password = random.ints(8, 'a', 'z' + 1)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return password;
    }


    public static void signIn() {
        boolean isTrue = true;
        while (isTrue) {

            System.out.print("Enter the current time (yyyy-MM-dd): ");
            String inputTime = scanner.nextLine();

            System.out.println("enter username :");
            String username = scanner.nextLine();

            System.out.println("enter password :");
            String password = scanner.nextLine();

            LocalDate currentTime = LocalDate.parse(inputTime, DateTimeFormatter.ISO_DATE);

            LocalDate firstStartDate = LocalDate.parse("yyyy-MM-dd", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate firstEndDate = firstStartDate.plusWeeks(1);

            LocalDate secondStartDate = LocalDate.parse("yyyy-MM-dd", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate secondEndDate = secondStartDate.plusWeeks(1);

            if (currentTime.isAfter(firstStartDate) && currentTime.isBefore(firstEndDate)) {
                registerLoan();
            } else if (currentTime.isAfter(secondStartDate) && currentTime.isBefore(secondEndDate)) {
                registerLoan();
            } else {
                System.out.println("User is outside the allowed time periods.");
            }


            optionalStudent = ApplicationContext.getStudentServiceImpl().login(username, password);

            if (optionalStudent.isPresent()) {
                //refrence to static method in menu
                isTrue = false;
            } else {
                System.out.println("username and password UnCorrect !!!!\n");
                System.out.println("Please Again ... ");


            }
        }
    }

    public static void registerLoan() {
        System.out.println("which loan you want ?");
        System.out.println("1.Education Loan");
        System.out.println("2.Housing Loan");
        System.out.println("3.Payment Loan");
        int select = scanner.nextInt();
        scanner.nextLine();

        switch (select) {
            case 1 -> {
                System.out.println("Enter your id :");
                int selectId = scanner.nextInt();
                scanner.nextLine();
                ApplicationContext.getLoanService().addEducationLoanToCard(selectId);
            }
            case 2 -> {
                System.out.println("Enter your id :");
                int selectId2 = scanner.nextInt();
                System.out.println("Write your city:");
                String city = scanner.nextLine().toUpperCase();
                scanner.nextLine();
                ApplicationContext.getLoanService().addHousingLoanOfStudent(selectId2, city);
            }


            case 3 -> {
                System.out.println("Enter your id :");
                int selectId3 = scanner.nextInt();
                scanner.nextLine();
                ApplicationContext.getLoanService().addPaymentOfUniversity(selectId3);
            }
        }
    }

    public static void showUserPanel(Integer id) {
        System.out.println("Enter your id to show :");
        int selectId4 = scanner.nextInt();
        scanner.nextLine();
    }

    public static void SaveCard() {
        Card card = new Card();
        System.out.println("Enter number of card : ");
        int numberOfCard = scanner.nextInt();
        card.setNumberOfCard(numberOfCard);

        System.out.println("Enter cvv2 of card :");
        int cvv2 = scanner.nextInt();
        card.setCvv2(cvv2);

        System.out.println("Enter expired Date of card :");
        LocalDate expiredDate = LocalDate.parse(scanner.next());
        card.setExpiredDate(expiredDate);

    }
}