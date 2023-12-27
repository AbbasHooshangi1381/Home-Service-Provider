package menu;

import entity.Card;
import entity.Student;
import entity.enumuration.MarriedOrSingle;
import entity.enumuration.SectionOfStudy;
import entity.enumuration.UniversityType;
import lombok.SneakyThrows;
import util.ApplicationContext;
import util.SecurityContext;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static menu.Menu.*;

public class SignInMenu {
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

            System.out.println("which city you live?");
            String city = scanner.next().toUpperCase();

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

            System.out.println("Enter a valid enter date to university like (yyyy-MM-dd):");
            LocalDate enterYear;
            enterYear = validateEnterDate();

            boolean gettingLoan = false;
            LocalDate lastTimeGetLoan = null;

            System.out.println("Are you getting loan until now? (write just 'true' or 'false')");

            while (true) {
                String input = scanner.next().trim().toLowerCase();

                if (input.equals("true")) {
                    gettingLoan = true;
                    System.out.println("Enter lastTimeGetLoan (write as yyyy-mm-dd): ");
                    input = scanner.next();

                    if (!input.isEmpty()) {
                        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        lastTimeGetLoan = LocalDate.parse(input, formatter1);
                    } else {
                        System.out.println("Invalid input for lastTimeGetLoan. Please provide a valid date.");
                    }

                    break;
                } else if (input.equals("false")) {
                    gettingLoan = false;
                    break;
                } else {
                    System.out.println("Invalid input. Please enter either 'true' or 'false'.");
                }
            }

            System.out.println("Are you getting Housing Loan Until Now in this grade of university? " +
                    "(write just (true) or (false))");
            boolean gettingHoisingLoan = false;

            while (true) {
                String input = scanner.next().trim().toLowerCase();

                if (input.equals("true")) {
                    gettingHoisingLoan = true;
                    break;
                } else if (input.equals("false")) {
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
                    break;
                } else {
                    System.out.println("Invalid input. Please enter either 'true' or 'false'.");
                }
            }


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


            System.out.println("choose section of study : \n 1.ASSOCIATE_DEGREE \n 2.CONTINUES_SENIOR \n 3.UNCONTINUES_SENIOR" +
                    " \n 4.PROFESSIONAL_DOCTOR \n 5.CONTINUOUS_PHD \n 6.UNCONTINUOUS_PHD \n 7.CONTINUES_MASTER \n 8.UNCONTINUES_MASTER  ");
            int Study = scanner.nextInt();
            SectionOfStudy sectionOfStudy = null;
            switch (Study) {
                case 1 -> sectionOfStudy = SectionOfStudy.ASSOCIATE_DEGREE;
                case 2 -> sectionOfStudy = SectionOfStudy.CONTINUES_SENIOR;
                case 3 -> sectionOfStudy = SectionOfStudy.UNCONTINUES_SENIOR;
                case 4 -> sectionOfStudy = SectionOfStudy.PROFESSIONAL_DOCTOR;
                case 5 -> sectionOfStudy = SectionOfStudy.CONTINUOUS_PHD;
                case 6 -> sectionOfStudy = SectionOfStudy.UNCONTINUOUS_PHD;
                case 7 -> sectionOfStudy = SectionOfStudy.CONTINUES_MASTER;
                case 8 -> sectionOfStudy = SectionOfStudy.UNCONTINUES_MASTER;
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

            Student student1 = new Student(firstName, lastName, motherName, shenasname, nationalCode, password ,DOB, nameOfUniversity,
                    numberOfTerm, enterYear, gettingLoan,gettingHoisingLoan, havingDorm, lastTimeGetLoan,
                    universityType, sectionOfStudy, marriedOrSingle, city);

            ApplicationContext.getStudentServiceImpl().save(student1);
            SecurityContext.fillContext(student1);

            System.out.println("ADD to DataBase SUCESSFULLY");
            System.out.println("--------------------------------");
            System.out.println("--------------------------------");

            firstMenu();

        }
    }


    public static String generateRandomPassword() {
        String uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String specialCharacters = "@#$%&";
        String lowercaseLettersAndNumbers = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        SecureRandom random = new SecureRandom();
        password.append(uppercaseLetters.charAt(random.nextInt(uppercaseLetters.length())));
        password.append(specialCharacters.charAt(random.nextInt(specialCharacters.length())));

        for (int i = 1; i < 8; i++) {
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
}
