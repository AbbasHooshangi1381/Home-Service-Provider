package menu;

import entity.Card;
import entity.Student;
import entity.enumuration.*;
import lombok.SneakyThrows;
import lombok.ToString;
import util.ApplicationContext;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static entity.enumuration.NameOfBank.MELLI;

@SuppressWarnings("unused")
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

    @SneakyThrows
    public static void signup() {
        boolean isTrue = true;
        while (true) {
            Student student = new Student();
            Card card = new Card();

            System.out.println("Enter firstName :  ");
            String firstName = scanner.next();


            System.out.println("Enter lastName : ");
            String lastName = scanner.next();


            System.out.println("Enter motherName :");
            String motherName = scanner.nextLine();

            System.out.println("Enter shenasname number : ");
            String shenasname = scanner.next();


            System.out.println("Enter nationalCode : ");
            String nationalCode = scanner.nextLine();


            System.out.println("Enter DOB : ");
            LocalDate DOB = LocalDate.parse(scanner.next());


            System.out.println("Enter name of university :");
            String nameOfUniversity = scanner.next();


            System.out.println("Enter number of term :");
            int numberOfTerm = scanner.nextInt();

            System.out.println("Enter enter year :");
            String enterYear = scanner.nextLine();


            System.out.println("Are you getting loan until now? (write just (true) or (false))");
            boolean gettingLoan = scanner.nextBoolean();


            System.out.println("Are you getting Dorm?(write just (true) or (false))");
            boolean havingDorm = scanner.nextBoolean();


            System.out.println("Enter the last time you get loan : dd/mm/yyyy");
            LocalDate lastTimeGetLoan = LocalDate.parse(scanner.next());

/*            String dateString = scanner.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate loanDate = LocalDate.parse(dateString, formatter);*/


            System.out.println("Enter your City :");
            String city = scanner.nextLine();


            System.out.println("choose university type: \n 1.AZAD_UNIVERSITY \n 2.DAILY_GOVERMENT \n 3.NIGHTLY_GOVERMENT" +
                    "\n 4.EXCESS_CAPACITY \n 5.NON_PROFIT_UNIVERSITIES \n 6.ELMI_CARBORDI \n7.PAARDIS \n 8.PAYAM_NOOR ");
            int typeOfUniversity = scanner.nextInt();
            UniversityType universityType = null;
            switch (typeOfUniversity) {
                case 1 -> universityType = UniversityType.AZAD_UNIVERSITY;
                case 2 -> universityType = UniversityType.DAILY_GOVERMENT;
                case 3 -> universityType = UniversityType.NIGHTLY_GOVERMENT;
                case 4 -> universityType = UniversityType.EXCESS_CAPACITY;
                case 5 -> universityType = UniversityType.NON_PROFIT_UNIVERSITIES;
                case 6 -> universityType = UniversityType.ELMI_CARBORDI;
                case 7 -> universityType = UniversityType.PAARDIS;
                case 8 -> universityType = UniversityType.PAYAM_NOOR;
            }


            System.out.println("choose section of study : \n 1.ASSOCIATE_DEGREE \n 2.MASTERS \n 3.PROFESSIONAL_DOCTOR" +
                    " \n 4.CONTINUOUS_PHD \n 5.UNCONTINUOUS_PHD");
            int Study = scanner.nextInt();
            SectionOfStudy sectionOfStudy = null;
            switch (Study) {
                case 1 -> sectionOfStudy = SectionOfStudy.ASSOCIATE_DEGREE;
                case 2 -> sectionOfStudy = SectionOfStudy.MASTERS;
                case 3 -> sectionOfStudy = SectionOfStudy.PROFESSIONAL_DOCTOR;
                case 4 -> sectionOfStudy = SectionOfStudy.CONTINUOUS_PHD;
                case 5 -> sectionOfStudy = SectionOfStudy.UNCONTINUOUS_PHD;

            }


            System.out.println("Are you married or single ?: \n 1.married \n 2.single");
            int marriedSingle = scanner.nextInt();
            MarriedOrSingle marriedOrSingle = null;
            switch (marriedSingle) {
                case 1 -> marriedOrSingle = MarriedOrSingle.MARRIED;
                case 2 -> marriedOrSingle = MarriedOrSingle.SINGLE;
            }

            System.out.println("ADD to DataBase SUCESSFULLY");

            String password = generatePassword();


            System.out.println("Username: " + student.getNationalCode());
            System.out.println("Password: " + student.getPassword());

            Student student1 = new Student(firstName, lastName, motherName, shenasname, nationalCode, DOB, nameOfUniversity,
                    numberOfTerm, enterYear, gettingLoan, havingDorm, lastTimeGetLoan, nationalCode, password,
                    universityType, sectionOfStudy, marriedOrSingle, city);

            System.out.println("Please enter The bank of your card, :");
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
                registerLoan();
                isTrue = false;
            } else {
                System.out.println("username and password UnCorrect !!!!\n");
                System.out.println("Please Again ... ");


            }
        }
        System.out.println("-------------------------------------------");
        System.out.println("1. register for loan :");
        System.out.println("2. refund :");//بازپرداخت
        System.out.println("show user panel : ");

        try {
            int select = scanner.nextInt();
            scanner.nextLine();
            switch (select) {
                case 1 -> registerLoan();

                case 2 -> refund();

                case 3 -> showUserPanel();

                default -> System.out.println("---Eror404---");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("!!!WRONG!!!");

        }

    }

    public static void registerLoan() {

        System.out.println("enter your id :");
        int input=scanner.nextInt();
        scanner.nextLine();
        ApplicationContext.getLoanService().graduatedStudent(input);

    }
    public static void registerLoanNotGraduated(){
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
                ApplicationContext.getLoanService().addHousingLoanOfStudent(selectId2, City.valueOf(city));
            }


            case 3 -> {
                System.out.println("Enter your id :");
                int selectId3 = scanner.nextInt();
                scanner.nextLine();

                System.out.println("Enter your university Type :");
                String universityType = scanner.nextLine().toUpperCase();
                ApplicationContext.getLoanService().addPaymentOfUniversity(selectId3, UniversityType.valueOf(universityType));
            }
        }
    }


    public static void showUserPanel() {
        System.out.println("Enter your id to show :");
        int selectId4 = scanner.nextInt();
        scanner.nextLine();
        ApplicationContext.getInstallmentService().showInstallments(selectId4);
    }

    public static void SaveCard() {

        System.out.println("please enter Name of bank : \n1.MELLI \n 2.REFAH \n3.TEJARAT \n 4.MASKAN");
        Integer name = scanner.nextInt();
        NameOfBank nameOfBank = null;
        switch (name) {
            case 1 -> {
                nameOfBank = MELLI;

            }

            case 2 -> {
                nameOfBank = NameOfBank.REFAH;

            }

            case 3 -> {
                nameOfBank = NameOfBank.TEJARAT;

            }

            case 4 -> {
                nameOfBank = NameOfBank.MASKAN;

            }

        }

        System.out.println("Enter number of card : ");
        Integer numberOfCard = scanner.nextInt();


        System.out.println("Enter cvv2 of card :");
        Integer cvv2 = scanner.nextInt();


        System.out.println("Enter expired Date of card :");
        LocalDate expiredDate = LocalDate.parse(scanner.next());

        Card card = new Card(nameOfBank, numberOfCard, expiredDate, cvv2);
        ApplicationContext.getCardService().save(card);

    }

    public static void refund() {
        System.out.println("Please enter your id :");
        Integer selectedId5=scanner.nextInt();
        ApplicationContext.getInstallmentService().graduatedStudent(selectedId5);
    }
    public static void showRepaymentOptions() {
        System.out.println("Repayment Options:");
        System.out.println("1. Paid Installments");
        System.out.println("2. Unpaid Installments");
        System.out.println("3. Pay Installment");
        Integer input=scanner.nextInt();
        scanner.nextLine();

        switch (input){
            case 1->
            case 2->
            case 3->

        }
    }



}