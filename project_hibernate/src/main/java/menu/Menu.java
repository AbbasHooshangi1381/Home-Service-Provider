package menu;

import entity.Card;
import entity.Student;
import entity.enumuration.*;
import lombok.SneakyThrows;
import lombok.ToString;
import util.ApplicationContext;
import validation.RegexValidation;

import java.security.SecureRandom;
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
            String firstName;
            firstName = validationFirstName();


            System.out.println("Enter lastName : ");
            String lastName;
            lastName = validationLastName();


            System.out.println("Enter motherName :");
            String motherName;
            motherName = validationMotherName();

            System.out.println("Enter shenasname number : ");
            Integer shenasname;
            shenasname = validationShenasname();

            System.out.println("Enter nationalCode : ");
            String nationalCode = scanner.next();


            LocalDate DOB = null;

            System.out.println("Enter DOB (write as yyyy-mm-dd): ");
            String dob = scanner.next();

            if (!dob.isEmpty()) {
                DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                DOB = LocalDate.parse(dob, formatter1);
            } else {
                System.out.println("Invalid input for DOB. Please provide a valid date.");
            }

            System.out.println("Enter name of university :");
            String nameOfUniversity;
            nameOfUniversity = validationNameOfUniversity();


            System.out.println("Enter number of term :");
            int numberOfTerm;
            numberOfTerm = validationNumberOfTerm();

            System.out.println("Enter enter year :");
            Integer enterYear;
            enterYear = validationNumberOfEnterYear();


/*            System.out.println("Are you getting loan until now? (write just (true) or (false))");
            boolean gettingLoan = scanner.nextBoolean();


            System.out.println("Are you getting Dorm?(write just (true) or (false))");
            boolean havingDorm = scanner.nextBoolean();*/

            System.out.println("Are you getting loan until now? (write just (true) or (false))");
            boolean gettingLoan = false;

            while (true) {
                String input = scanner.next().trim().toLowerCase();

                if (input.equals("true")) {
                    gettingLoan = true;
                    break;
                } else if (input.equals("false")) {
                    gettingLoan = false;
                    break;
                } else {
                    System.out.println("Invalid input. Please enter either 'true' or 'false'.");
                }
            }


            System.out.println("Are you getting Dorm? (write just (true) or (false))");
            boolean havingDorm = false;

            while (true) {
                String input = scanner.next().trim().toLowerCase();

                if (input.equals("true")) {
                    havingDorm = true;
                    break;
                } else if (input.equals("false")) {
                    havingDorm = false;
                    break;
                } else {
                    System.out.println("Invalid input. Please enter either 'true' or 'false'.");
                }
            }


            LocalDate lastTimeGetLoan = null;

            System.out.println("Enter lastTimeGetLoan (write as yyyy-mm-dd): ");
            String input = scanner.next();

            if (!input.isEmpty()) {
                DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                lastTimeGetLoan = LocalDate.parse(input, formatter1);
            } else {
                System.out.println("Invalid input for DOB. Please provide a valid date.");
            }

/*            System.out.println("Enter the last time you get loan: yyyy-MM-dd");
            String input = scanner.nextLine();

            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate lastTimeGetLoan = LocalDate.parse(input, formatter2);*/


            System.out.println("Enter your City :");
            String city = scanner.nextLine();


            System.out.println("Choose university type: \n1. AZAD_UNIVERSITY \n2. DAILY_GOVERMENT \n3. NIGHTLY_GOVERMENT" +
                    "\n4. EXCESS_CAPACITY \n5. NON_PROFIT_UNIVERSITIES \n6. ELMI_CARBORDI \n7. PAARDIS \n8. PAYAM_NOOR");
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
                default -> System.out.println("Invalid input. Please enter a valid university type.");

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
                default -> System.out.println("Invalid input. Please enter a valid section of study.");

            }


            System.out.println("Are you married or single ?: \n 1.married \n 2.single");
            int marriedSingle = scanner.nextInt();
            MarriedOrSingle marriedOrSingle = null;
            switch (marriedSingle) {
                case 1 -> marriedOrSingle = MarriedOrSingle.MARRIED;
                case 2 -> marriedOrSingle = MarriedOrSingle.SINGLE;
                default -> System.out.println("Invalid input. Please enter a valid marital status.");
            }


            String password = generateRandomPassword();


            System.out.println("Username: " + nationalCode);
            System.out.println("Password: " + password);

            Student student1 = new Student(firstName, lastName, motherName, shenasname, nationalCode, DOB, nameOfUniversity,
                    numberOfTerm, enterYear, gettingLoan, havingDorm, lastTimeGetLoan,
                    universityType, sectionOfStudy, marriedOrSingle, city);
            ApplicationContext.getStudentServiceImpl().save(student1);
            System.out.println("ADD to DataBase SUCESSFULLY");
            System.out.println("--------------------------------");
            System.out.println("--------------------------------");

            System.out.println("Do you want to save Your Card now ? 1.Yes  2. No");
            int saveCards = scanner.nextInt();
            switch (saveCards) {
                case 1 -> {
                    saveCard();
                }
                case 2 -> {
                    System.out.println("thank you , bye !");
                    break;
                }
                default -> {
                    System.out.println("wrong select !!");
                    break;
                }
            }
        }
    }

    public static void saveCard() {
        System.out.println("which bank you have account : 1.MELLI 2.MASKAN 3.REFAH 4.TEJARAT");
        int nameOfBank = scanner.nextInt();
        NameOfBank nameOfBank1 = null;
        switch (nameOfBank) {
            case 1 -> nameOfBank1 = NameOfBank.MELLI;
            case 2 -> nameOfBank1 = NameOfBank.MASKAN;
            case 3 -> nameOfBank1 = NameOfBank.REFAH;
            case 4 -> nameOfBank1 = NameOfBank.TEJARAT;

            default -> System.out.println("Invalid input. Please enter a valid name of bank .");

        }
        System.out.println("Enter the number of card ");
        int numberOfCard=scanner.nextInt();

        LocalDate expiredDate = null;

        System.out.println("Enter time of expire date (write as yyyy-mm-dd): ");
        String input = scanner.nextLine();

        if (!input.isEmpty()) {
            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            expiredDate = LocalDate.parse(input, formatter1);
        } else {
            System.out.println("Invalid input for time. Please provide a valid time.");
        }

        System.out.println("please enter cvv2 of card :");
        int cvv2=scanner.nextInt();

        Card cards=new Card(nameOfBank1,numberOfCard,expiredDate,cvv2);
        ApplicationContext.getCardService().save(cards);
        System.out.println("Card succefully added to data base !!");

    }


    public static String generateRandomPassword() {
        String uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String specialCharacters = "!@#$%^&";
        String lowercaseLettersAndNumbers = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        SecureRandom random = new SecureRandom();
        password.append(uppercaseLetters.charAt(random.nextInt(uppercaseLetters.length())));
        password.append(specialCharacters.charAt(random.nextInt(specialCharacters.length())));

        for (int i = 2; i < 8; i++) {
            int randomIndex = random.nextInt(lowercaseLettersAndNumbers.length());
            char randomChar = lowercaseLettersAndNumbers.charAt(randomIndex);
            password.append(randomChar);
        }
        String shuffledPassword = shuffleString(password.toString());
        return shuffledPassword;
    }

    private static String shuffleString(String input) {
        char[] characters = input.toCharArray();
        for (int i = characters.length - 1; i > 0; i--) {
            int randomIndex = (int) (Math.random() * (i + 1));
            char temp = characters[i];
            characters[i] = characters[randomIndex];
            characters[randomIndex] = temp;
        }
        return new String(characters);
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
        int input = scanner.nextInt();
        scanner.nextLine();
        ApplicationContext.getLoanService().graduatedStudentForMenu(input);

    }

    public static void registerLoanNotGraduated() {
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
        Integer selectedId5 = scanner.nextInt();
        ApplicationContext.getInstallmentService().graduatedStudent(selectedId5);
    }

    public static void showRepaymentOptions() {
        System.out.println("Repayment Options:");
        System.out.println("1. Installments that paid in past");
        System.out.println("2. Unpaid Installments");
        System.out.println("3. Pay Installment");
        Integer input = scanner.nextInt();
        scanner.nextLine();

        switch (input) {
            case 1 -> paidInstallments();
            case 2 -> unPaidInstallments();
            case 3 -> payInstallments();

        }
    }

    public static void payInstallments() {

        System.out.println("please enter the payNumber:");
        int payNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.println("please enter your id:");
        int id = scanner.nextInt();
        scanner.nextLine();

        ApplicationContext.getInstallmentService().payInstallments(payNumber, id);
    }

    public static void paidInstallments() {

        System.out.println("please enter the payNumber:");
        int payNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.println("please enter your id:");
        int id = scanner.nextInt();
        scanner.nextLine();

        ApplicationContext.getInstallmentService().paidInstallments(payNumber, id);
    }

    public static void unPaidInstallments() {

        System.out.println("please enter the payNumber:");
        int payNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.println("please enter your id:");
        int id = scanner.nextInt();
        scanner.nextLine();

        ApplicationContext.getInstallmentService().unpaidInstallments(payNumber, id);
    }

    public static String validationFirstName() {
        String firstname = null;
        boolean isTrue = true;
        while (isTrue) {
            firstname = scanner.nextLine();
            if (RegexValidation.validatonFirstname(firstname)) {
                isTrue = false;
            } else {
                System.out.println("put write word!!!!");
            }
        }
        return firstname;
    }

    public static String validationLastName() {
        String lastName = null;
        boolean isTrue = true;
        while (isTrue) {
            lastName = scanner.nextLine();
            if (RegexValidation.validatonLastname(lastName)) {
                isTrue = false;
            } else {
                System.out.println("put write word!!!!");
            }
        }
        return lastName;
    }

    public static String validationMotherName() {
        String motherName = null;
        boolean isTrue = true;
        while (isTrue) {
            motherName = scanner.nextLine();
            if (RegexValidation.validatonMothername(motherName)) {
                isTrue = false;
            } else {
                System.out.println("put write word!!!!");
            }
        }
        return motherName;
    }

    public static Integer validationShenasname() {
        Integer shenasname = null;
        boolean isTrue = true;
        while (isTrue) {
            shenasname = Integer.parseInt(scanner.next());
            if (RegexValidation.validationShenasnameNumber(shenasname)) {
                isTrue = false;
            } else {
                System.out.println("wrong number you put !!!!");
            }
        }
        return shenasname;
    }


    public static String validationNameOfUniversity() {
        String nameOfUniversity = null;
        boolean isTrue = true;
        while (isTrue) {
            nameOfUniversity = scanner.next();
            if (RegexValidation.validationNameOfUniversity(nameOfUniversity)) {
                isTrue = false;
            } else {
                System.out.println("put write word!!!!");
            }
        }
        return nameOfUniversity;
    }

    public static Integer validationNumberOfTerm() {
        Integer numberOfTerm = null;
        boolean isTrue = true;
        while (isTrue) {
            numberOfTerm = scanner.nextInt();
            if (RegexValidation.validationNumberOfTerm(numberOfTerm)) {
                isTrue = false;
            } else {
                System.out.println("wrong number you put !!!!");
            }
        }
        return numberOfTerm;
    }

    public static Integer validationNumberOfEnterYear() {
        Integer numberOfEnterYear = null;
        boolean isTrue = true;
        while (isTrue) {
            numberOfEnterYear = scanner.nextInt();
            if (RegexValidation.validationEnterYear(numberOfEnterYear)) {
                isTrue = false;
            } else {
                System.out.println("wrong number you put !!!!");
            }
        }
        return numberOfEnterYear;
    }


}