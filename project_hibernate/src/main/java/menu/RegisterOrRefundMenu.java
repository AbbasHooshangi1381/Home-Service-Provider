package menu;

import entity.*;
import entity.enumuration.*;
import util.ApplicationContext;
import util.DatesApp;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static menu.Menu.*;


public class RegisterOrRefundMenu {

    public static void registerOrRefund() {
        System.out.println("-------------------------------------------");
        System.out.println("1. register for loan :");
        System.out.println("2. refund :");//بازپرداخت
        System.out.println("3.show user panel : ");

        try {
            int select = scanner.nextInt();
            scanner.nextLine();
            switch (select) {
                case 1 -> checkAndRegisterTimeOfLoan();

                case 2 -> refund();

                case 3 -> showUserPanel();

                default -> System.out.println("---Eror404---");
            }
        } catch (
                Exception e) {
            e.printStackTrace();
            System.out.println("!!!WRONG!!!");

        }

    }

    public static String checkAndRegisterTimeOfLoan() throws SQLException {
        System.out.print("Enter the current time (yyyy-MM-dd): ");
        String inputTime = scanner.nextLine();

        LocalDate currentTime = LocalDate.parse(inputTime, DateTimeFormatter.ISO_DATE);

        LocalDate firstStartDate = LocalDate.parse("1402-08-01", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate firstEndDate = firstStartDate.plusWeeks(1);

        LocalDate secondStartDate = LocalDate.parse("1402-11-25", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate secondEndDate = secondStartDate.plusWeeks(1);

        if (currentTime.isAfter(firstStartDate) && currentTime.isBefore(firstEndDate)) {
            registerLoan();
        } else if (currentTime.isAfter(secondStartDate) && currentTime.isBefore(secondEndDate)) {
            registerLoan();
        } else {
            System.out.println("User is outside the allowed time periods.");

        }
        return inputTime;
    }


    public static void registerLoan() throws SQLException {
        boolean isTrue = true;
        while (true) {

            if (optionalStudent.isPresent()) {
                LocalDate localDates = optionalStudent.get().getEnterYear();
                SectionOfStudy sectionOfStudy = optionalStudent.get().getSectionOfStudy();

                if (sectionOfStudy.equals(SectionOfStudy.ASSOCIATE_DEGREE)) {
                    LocalDate newDateForAssociateDegree = localDates.plusYears(4);
                    if (DatesApp.dateOfSystem.isAfter(newDateForAssociateDegree)) {
                        System.out.println("you are graduated, so you can not get loan");
                    } else {
                        saveCard();
                    }
                } else if (sectionOfStudy.equals(SectionOfStudy.MASTERS) ||
                        sectionOfStudy.equals(SectionOfStudy.UNCONTINUES_SENIOR)) {
                    LocalDate newDateForMaster = localDates.plusYears(2);
                    if (DatesApp.dateOfSystem.isAfter(newDateForMaster)) {
                        System.out.println("you are graduated, so you can not get loan");
                    } else {
                        saveCard();
                    }
                } else if (sectionOfStudy.equals(SectionOfStudy.CONTINUES_SENIOR)) {
                    LocalDate newDateForContinuesSenior = localDates.plusYears(6);
                    if (DatesApp.dateOfSystem.isAfter(newDateForContinuesSenior)) {
                        System.out.println("you are graduated, so you can not get loan");
                    } else {
                        saveCard();
                    }
                } else if (sectionOfStudy.equals(SectionOfStudy.UNCONTINUOUS_PHD) ||
                        sectionOfStudy.equals(SectionOfStudy.PROFESSIONAL_DOCTOR)) {
                    LocalDate newDateForDoctor = localDates.plusYears(5);
                    if (DatesApp.dateOfSystem.isAfter(newDateForDoctor)) {
                        System.out.println("you are graduated, so you can not get loan");
                    } else {
                        saveCard();
                    }
                }

            }
            break;
        }
    }

    public static void saveCard() throws SQLException {
        if (optionalStudent.isPresent()) {
            Card card = new Card();
            Integer id = optionalStudent.get().getId();
            Student student1 = ApplicationContext.getStudentServiceImpl().findById(id).orElse(null);

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
            String numberOfCard;
            numberOfCard = validationNumberOfCard();

            LocalDate expiredDate = null;

            System.out.println("Enter time of expire date (write as yyyy-mm-dd): ");
            String input = scanner.next();

            if (!input.isEmpty()) {
                DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                expiredDate = LocalDate.parse(input, formatter1);
            } else {
                System.out.println("Invalid input for time. Please provide a valid time.");
            }

            System.out.println("please enter cvv2 of card :");
            int cvv2 = scanner.nextInt();

            card.setNameOfBank(nameOfBank1);
            card.setNumberOfCard(numberOfCard);
            card.setExpiredDate(expiredDate);
            card.setStudent(student1);
            ApplicationContext.getCardService().save(card);

            System.out.println("Card succefully added to data base !!");
            registerLoanNotGraduated();
        }
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
                Loan loan = new Loan();
                Card card = new Card();
                Installment installment = new Installment();

                if (optionalStudent.isPresent()) {
                    Integer id = optionalStudent.get().getId();
                    Student student1 = ApplicationContext.getStudentServiceImpl().findById(id).orElse(null);

                    LocalDate lastLoanDates = optionalStudent.get().getLastLoanDate();
                    SectionOfStudy sectionOfStudy = optionalStudent.get().getSectionOfStudy();
                    double amount = 0.00;
                    if (lastLoanDates != null) {
                        if (isLoanDateEligible(lastLoanDates)) {

                            if (sectionOfStudy == SectionOfStudy.ASSOCIATE_DEGREE || sectionOfStudy == SectionOfStudy.MASTERS
                                    || sectionOfStudy == SectionOfStudy.CONTINUES_SENIOR || sectionOfStudy == SectionOfStudy.UNCONTINUES_SENIOR) {
                                amount = 1900000.00;
                            } else if (sectionOfStudy == SectionOfStudy.PROFESSIONAL_DOCTOR || sectionOfStudy == SectionOfStudy.CONTINUOUS_PHD) {
                                amount = 22500000.00;
                            } else if (sectionOfStudy == SectionOfStudy.UNCONTINUOUS_PHD) {
                                amount = 2600000.00;
                            }
                        }
                    } else {

                        if (sectionOfStudy == SectionOfStudy.ASSOCIATE_DEGREE || sectionOfStudy == SectionOfStudy.MASTERS
                                || sectionOfStudy == SectionOfStudy.CONTINUES_SENIOR || sectionOfStudy == SectionOfStudy.UNCONTINUES_SENIOR) {
                            amount = 1900000.00;
                        } else if (sectionOfStudy == SectionOfStudy.PROFESSIONAL_DOCTOR || sectionOfStudy == SectionOfStudy.CONTINUOUS_PHD) {
                            amount = 22500000.00;
                        } else if (sectionOfStudy == SectionOfStudy.UNCONTINUOUS_PHD) {
                            amount = 2600000.00;
                        }
                    }

                    if (amount != 0) {
                        loan.setCountOfLoan(amount);
                        loan.setDateOfStartLoan(LocalDate.now());
                        loan.setStudent(student1);
                        card.setAmountOfCard(amount);
                        card.setStudent(student1);
                        assert student1 != null;
                        student1.setLastLoanDate(LocalDate.now());
                        installment.setLoanStatus(LoanStatus.INCOMPLETE_PAID);

                        installment.setLoan(loan);

                        ApplicationContext.getStudentServiceImpl().save(student1);
                        ApplicationContext.getLoanService().save(loan);
                        ApplicationContext.getCardService().save(card);
                        ApplicationContext.getInstallmentService().save(installment);
                        System.out.println("The amount of loan is added to your card.");
                        registerOrRefund();
                    }


                } else System.out.println("Failed to add the amount of loan to your card.");
                registerOrRefund();


            }


            case 2 -> {
                Loan loan = new Loan();
                Card card = new Card();
                Installment installment = new Installment();

                if (optionalStudent.isPresent()) {
                    Integer id = optionalStudent.get().getId();
                    Student student1 = ApplicationContext.getStudentServiceImpl().findById(id).orElse(null);
                    City[] cities = City.values();
                    MarriedOrSingle marriedOrSingle = optionalStudent.get().getMarriedOrSingle();
                    Boolean gettingLoan = optionalStudent.get().getGettingLoan();
                    Boolean havingDorm = optionalStudent.get().getHavingDorm();
                    String city = optionalStudent.get().getCity();


                    for (City enumCity : cities) {
                        if (enumCity.name().equalsIgnoreCase(city)) {

                            if (!gettingLoan && !havingDorm &&
                                    marriedOrSingle == MarriedOrSingle.MARRIED) {
                                installment.setLoanStatus(LoanStatus.INCOMPLETE_PAID);
                                card.setStudent(student1);
                                loan.setCountOfLoan(32000000.00);
                                loan.setDateOfStartLoan(LocalDate.now());
                                loan.setStudent(student1);
                                card.setAmountOfCard(32000000.00);
                                card.setStudent(student1);
                                assert student1 != null;
                                student1.setLastLoanDate(LocalDate.now());
                            }


                        } else if (Objects.equals(city, "TEHRAN")) {
                            card.setStudent(student1);
                            loan.setCountOfLoan(26000000.00);
                            loan.setDateOfStartLoan(LocalDate.now());
                            loan.setStudent(student1);
                            card.setAmountOfCard(26000000.00);
                            card.setStudent(student1);
                            assert student1 != null;
                            student1.setLastLoanDate(LocalDate.now());

                        } else {
                            loan.setCountOfLoan(19500000.00);
                            loan.setDateOfStartLoan(LocalDate.now());
                            loan.setStudent(student1);
                            card.setAmountOfCard(19500000.00);
                            card.setStudent(student1);
                            assert student1 != null;
                            student1.setLastLoanDate(LocalDate.now());
                        }

                    }
                    ApplicationContext.getStudentServiceImpl().save(student1);
                    ApplicationContext.getCardService().save(card);
                    ApplicationContext.getLoanService().save(loan);
                    System.out.println("The amount of loan is added to your card.");
                    System.out.println("now add the information of your wife and the location of your house :");
                    addWife();

                }
                registerOrRefund();
            }


            case 3 -> {

                Loan loan = new Loan();
                Card card = new Card();
                Installment installment = new Installment();

                if (optionalStudent.isPresent()) {
                    Integer id = optionalStudent.get().getId();
                    Student student1 = ApplicationContext.getStudentServiceImpl().findById(id).orElse(null);
                    UniversityType universityType = optionalStudent.get().getUniversityType();
                    LocalDate lastLoanDate1 = optionalStudent.get().getLastLoanDate();
                    SectionOfStudy sectionOfStudy = optionalStudent.get().getSectionOfStudy();

                    if (universityType == UniversityType.DAILY_GOVERMENT || universityType == UniversityType.NIGHTLY_GOVERMENT) {
                        System.out.println("You study in a government university, so you cannot get this loan!");
                    } else {
                        double amount = 0.00;
                        if (lastLoanDate1 != null) {
                            if (isLoanDateEligible(lastLoanDate1)) {
                                if (sectionOfStudy == SectionOfStudy.ASSOCIATE_DEGREE || sectionOfStudy == SectionOfStudy.MASTERS
                                        || sectionOfStudy == SectionOfStudy.CONTINUES_SENIOR || sectionOfStudy == SectionOfStudy.UNCONTINUES_SENIOR) {
                                    amount = 1900000.00;
                                } else if (sectionOfStudy == SectionOfStudy.PROFESSIONAL_DOCTOR || sectionOfStudy == SectionOfStudy.CONTINUOUS_PHD) {
                                    amount = 2250000.00;
                                } else if (sectionOfStudy == SectionOfStudy.UNCONTINUOUS_PHD) {
                                    amount = 2600000.00;
                                }
                            }
                        } else {
                            if (sectionOfStudy == SectionOfStudy.ASSOCIATE_DEGREE || sectionOfStudy == SectionOfStudy.MASTERS
                                    || sectionOfStudy == SectionOfStudy.CONTINUES_SENIOR || sectionOfStudy == SectionOfStudy.UNCONTINUES_SENIOR) {
                                amount = 1900000.00;
                            } else if (sectionOfStudy == SectionOfStudy.PROFESSIONAL_DOCTOR || sectionOfStudy == SectionOfStudy.CONTINUOUS_PHD) {
                                amount = 2250000.00;
                            } else if (sectionOfStudy == SectionOfStudy.UNCONTINUOUS_PHD) {
                                amount = 2600000.00;
                            }
                        }

                        if (amount != 0.00) {
                            loan.setCountOfLoan(amount);
                            loan.setDateOfStartLoan(LocalDate.now());
                            loan.setStudent(student1);
                            card.setAmountOfCard(amount);
                            card.setStudent(student1);
                            assert student1 != null;
                            student1.setLastLoanDate(LocalDate.now());
                        }
                    }

                    ApplicationContext.getLoanService().save(loan);
                    ApplicationContext.getStudentServiceImpl().save(student1);
                    ApplicationContext.getCardService().save(card);
                    System.out.println("The amount of the loan is added to your card.");
                    registerOrRefund();
                }
                registerOrRefund();
            }


        }

    }

    public static void addWife() {
        System.out.println("Enter firstName :  ");
        String firstName;
        firstName = validationFirstName();


        System.out.println("Enter lastName : ");
        String lastName;
        lastName = validationLastName();

        System.out.println("Enter your location  :  ");
        String location = scanner.next();


        System.out.println("Enter number of renting : ");
        String numberOfRenting = scanner.next();

        WifeAndHome wifeAndHome = new WifeAndHome(firstName, lastName, location, numberOfRenting);
        System.out.println("information of wife and house is added to database !");
        ApplicationContext.getWifeAndHomeService().save(wifeAndHome);


    }


    public static boolean isLoanDateEligible(LocalDate lastLoanDate) {
        LocalDate nextEligibleDate = lastLoanDate.plusMonths(6);
        LocalDate currentDate = LocalDate.now();

        return currentDate.isAfter(nextEligibleDate);
    }



    public static void showUserPanel() {
        List<Installment> installmentList = new ArrayList<>();
        Installment installment = new Installment();

        System.out.println("ID of loan : ");
        int id2 = scanner.nextInt();

        if (optionalStudent.isPresent()) {
            Integer id = optionalStudent.get().getId();
            Student student1 = ApplicationContext.getStudentServiceImpl().findById(id).orElse(null);
            assert student1 != null;
            Loan loan1 = ApplicationContext.getLoanService().findById(id2).orElse(null);

            Double countOfLoan = student1.getLoans().get(0).getCountOfLoan();
            double amountOfLoanWithProfit = countOfLoan * 0.04;
            int installmentCounter = 1;

            for (int i = 1; i <= 5; i++) {
                System.out.println("Year " + i + ":");

                for (int j = 1; j <= 12; j++) {
                    double installmentAmount = amountOfLoanWithProfit * Math.pow(2, i - 1) / 12;
                    System.out.println("Month " + j + ": " + installmentAmount);

                    Installment newInstallment = new Installment();
                    newInstallment.setAmountOfEachInstallment(installmentAmount);
                    newInstallment.setTimeOfPayInstallment(LocalDate.of(i, j, 1));
                    newInstallment.setLoanStatus(LoanStatus.INCOMPLETE_PAID);
                    newInstallment.setPayNumber(installmentCounter);
                    newInstallment.setLoan(loan1);
                    assert loan1 != null;
                    loan1.setStudent(student1);

                    installmentList.add(newInstallment);
                    installmentCounter++;
                }
            }

            ApplicationContext.getInstallmentService().saveAll(installmentList);
            ApplicationContext.getLoanService().save(loan1);
            System.out.println("installments are saved to the database.");
        } else
            System.out.println("installments (not) save to dataBase .");
        registerOrRefund();

    }



    public static void refund() {

        if (optionalStudent.isPresent()) {
            LocalDate localDate = optionalStudent.get().getEnterYear();
            SectionOfStudy sectionOfStudy = optionalStudent.get().getSectionOfStudy();


            if (sectionOfStudy.equals(SectionOfStudy.ASSOCIATE_DEGREE)) {
                LocalDate newDateForAssociateDegree = localDate.plusYears(4);
                if (DatesApp.dateOfSystem.isAfter(newDateForAssociateDegree)) {
                    showRepaymentOptions();
                } else {
                    System.out.println("you can not access to repayment menu.");
                }
            } else if (sectionOfStudy.equals(SectionOfStudy.MASTERS) ||
                    sectionOfStudy.equals(SectionOfStudy.UNCONTINUES_SENIOR)) {
                LocalDate newDateForMaster = localDate.plusYears(2);
                if (DatesApp.dateOfSystem.isAfter(newDateForMaster)) {
                    showRepaymentOptions();
                } else {
                    System.out.println("you can not access to repayment menu.");
                }
            } else if (sectionOfStudy.equals(SectionOfStudy.CONTINUES_SENIOR)) {
                LocalDate newDateForContinuesSenior = localDate.plusYears(6);
                if (DatesApp.dateOfSystem.isAfter(newDateForContinuesSenior)) {
                    showRepaymentOptions();
                } else {
                    System.out.println("you can not access to repayment menu.");
                }
            } else if (sectionOfStudy.equals(SectionOfStudy.UNCONTINUOUS_PHD) ||
                    sectionOfStudy.equals(SectionOfStudy.PROFESSIONAL_DOCTOR)) {
                LocalDate newDateForDoctor = localDate.plusYears(5);
                if (DatesApp.dateOfSystem.isAfter(newDateForDoctor)) {
                    showRepaymentOptions();
                } else {
                    System.out.println("you can not access to repayment menu.");
                }
            }

        }
        System.out.println("........................");
     //   signIn();
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

        if (optionalStudent.isPresent()) {
            Integer id = optionalStudent.get().getId();
            ApplicationContext.getInstallmentService().paidInstallments(id).forEach(System.out::println);
        }
    }


    public static void unPaidInstallments() {
        if (optionalStudent.isPresent()) {
            Integer id = optionalStudent.get().getId();
            ApplicationContext.getInstallmentService().unpaidInstallments(id).stream().collect(Collectors.toList());

        }
    }


}
