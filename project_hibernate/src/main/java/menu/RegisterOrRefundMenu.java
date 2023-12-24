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

        try {
            int select = scanner.nextInt();
            scanner.nextLine();
            switch (select) {
                case 1 -> checkAndRegisterTimeOfLoan();

                case 2 -> refund();

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
            saveCard();
        } else if (currentTime.isAfter(secondStartDate) && currentTime.isBefore(secondEndDate)) {
            saveCard();
        } else {
            System.out.println("User is outside the allowed time periods.");

        }
        return inputTime;
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
            chooseEducationOrPaymentOrHousingLoanToGetFromUniversity();

        }
    }

    public static void chooseEducationOrPaymentOrHousingLoanToGetFromUniversity() {
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

                            if (sectionOfStudy == SectionOfStudy.ASSOCIATE_DEGREE
                                    || sectionOfStudy == SectionOfStudy.CONTINUES_SENIOR ||
                                    sectionOfStudy == SectionOfStudy.UNCONTINUES_SENIOR) {
                                amount = 1900000.00;
                            } else if (sectionOfStudy == SectionOfStudy.PROFESSIONAL_DOCTOR ||
                                    sectionOfStudy == SectionOfStudy.CONTINUOUS_PHD ||
                                    sectionOfStudy == SectionOfStudy.CONTINUES_MASTER
                                    || sectionOfStudy == SectionOfStudy.UNCONTINUES_MASTER) {
                                amount = 2250000.00;
                            } else if (sectionOfStudy == SectionOfStudy.UNCONTINUOUS_PHD) {
                                amount = 2600000.00;
                            }
                        }
                    } else {

                        if (sectionOfStudy == SectionOfStudy.ASSOCIATE_DEGREE
                                || sectionOfStudy == SectionOfStudy.CONTINUES_SENIOR ||
                                sectionOfStudy == SectionOfStudy.UNCONTINUES_SENIOR) {
                            amount = 1900000.00;
                        } else if (sectionOfStudy == SectionOfStudy.PROFESSIONAL_DOCTOR ||
                                sectionOfStudy == SectionOfStudy.CONTINUOUS_PHD ||
                                sectionOfStudy == SectionOfStudy.CONTINUES_MASTER
                                || sectionOfStudy == SectionOfStudy.UNCONTINUES_MASTER) {
                            amount = 2250000.00;
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
                        showUserPanel();
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
                    Boolean gettingHousingLoan = optionalStudent.get().getGettingHousingLoan();
                    Boolean havingDorm = optionalStudent.get().getHavingDorm();
                    String city = optionalStudent.get().getCity();


                    for (City enumCity : cities) {
                        if (enumCity.name().equalsIgnoreCase(city)) {

                            if (!gettingHousingLoan && !havingDorm &&
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


                        } else if (!gettingHousingLoan && !havingDorm &&
                                marriedOrSingle == MarriedOrSingle.MARRIED && Objects.equals(city, "TEHRAN")) {
                            card.setStudent(student1);
                            loan.setCountOfLoan(26000000.00);
                            loan.setDateOfStartLoan(LocalDate.now());
                            loan.setStudent(student1);
                            card.setAmountOfCard(26000000.00);
                            card.setStudent(student1);
                            assert student1 != null;
                            student1.setLastLoanDate(LocalDate.now());

                        } else if (!gettingHousingLoan && !havingDorm &&
                                marriedOrSingle == MarriedOrSingle.MARRIED) {
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
                                if (sectionOfStudy == SectionOfStudy.ASSOCIATE_DEGREE
                                        || sectionOfStudy == SectionOfStudy.CONTINUES_SENIOR ||
                                        sectionOfStudy == SectionOfStudy.UNCONTINUES_SENIOR) {
                                    amount = 1300000.00;
                                } else if (sectionOfStudy == SectionOfStudy.PROFESSIONAL_DOCTOR
                                        || sectionOfStudy == SectionOfStudy.CONTINUOUS_PHD ||
                                        sectionOfStudy == SectionOfStudy.UNCONTINUES_MASTER ||
                                        sectionOfStudy == SectionOfStudy.CONTINUES_MASTER) {
                                    amount = 2600000.00;
                                } else if (sectionOfStudy == SectionOfStudy.UNCONTINUOUS_PHD) {
                                    amount = 6500000.00;
                                }
                            }
                        } else {
                            if (sectionOfStudy == SectionOfStudy.ASSOCIATE_DEGREE
                                    || sectionOfStudy == SectionOfStudy.CONTINUES_SENIOR ||
                                    sectionOfStudy == SectionOfStudy.UNCONTINUES_SENIOR) {
                                amount = 1300000.00;
                            } else if (sectionOfStudy == SectionOfStudy.PROFESSIONAL_DOCTOR ||
                                    sectionOfStudy == SectionOfStudy.CONTINUOUS_PHD ||
                                    sectionOfStudy == SectionOfStudy.UNCONTINUES_MASTER ||
                                    sectionOfStudy == SectionOfStudy.CONTINUES_MASTER) {
                                amount = 2600000.00;
                            } else if (sectionOfStudy == SectionOfStudy.UNCONTINUOUS_PHD) {
                                amount = 6500000.00;
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
                    showUserPanel();
                } else
                    System.out.println("the amount of loan did not add to your card !");
                registerOrRefund();
            }


        }
    }

    public static void addWife() {
        WifeAndHome wifeAndHome = new WifeAndHome();
        if (optionalStudent.isPresent()) {
            Integer id = optionalStudent.get().getId();
            Student student1 = ApplicationContext.getStudentServiceImpl().findById(id).orElse(null);

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

            wifeAndHome.setFirstName(firstName);
            wifeAndHome.setLastName(lastName);
            wifeAndHome.setLocationOfHouse(location);
            wifeAndHome.setNumberOfRentHouse(numberOfRenting);
            wifeAndHome.setStudent(student1);
            ApplicationContext.getWifeAndHomeService().save(wifeAndHome);
            System.out.println("information of wife and house is added to database !");
            showUserPanel();


        }
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
            registerOrRefund();
        } else
            System.out.println("installments (not) save to dataBase .");
        registerOrRefund();

    }


}
