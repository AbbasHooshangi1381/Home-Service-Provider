
package menu;

import domain.enumurations.StatusOfOrder;
import domain.other.CustomerOrder;
import domain.serviceEntity.SubService;
import domain.userEntity.Customer;
import util.ApplicationContext;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static menu.BaseMenu.*;
import static validation.RegexValidation.validateEmail;
import static validation.RegexValidation.validationName;
import static validation.Validation.generateRandomPassword;

@SuppressWarnings("unused")
public class CustomerMenu {
    public static Optional<Customer> customerOptional;

    public static void signInOrSignUpCustomer() throws SQLException {

        System.out.println("1. sign in :");
        System.out.println("2. sign up :");


        try {
            int select = scanner.nextInt();
            scanner.nextLine();
            switch (select) {
                case 1 -> signInCustomer();

                case 2 -> signUpCustomer();

                default -> System.out.println("---Error404---");
            }
        } catch (
                Exception e) {
            //  e.printStackTrace();
            System.out.println("!!!WRONG!!!");

        }

    }

    public static void signInCustomer() throws SQLException {
        boolean isTrue = true;
        while (isTrue) {

            String username = "alfef526";


            String password = "Bxfy3s#5";

            customerOptional = ApplicationContext.getCustomerService().login(username, password);


            if (customerOptional.isPresent()) {
                System.out.println("you can use system : ");
                customerAfterSignIn();
                isTrue = false;
            } else {
                System.out.println("username and password UnCorrect !!!!\n");
                System.out.println("Please Again ... ");
                firstMenu();

            }

        }
    }

    public static void signUpCustomer() {

        String FirstName = "abbas";
        String validatedFirstName = validationNames(FirstName);

        String LastName = "aaaa";
        String validatedLastName = validationNames(LastName);

        String newEmailOFExpert = "";

        String emailOfExpert = "basjfryan@gmail.com";
        String validatedEmail = validationEmails(emailOfExpert);

        List<String> list = ApplicationContext.getCustomerService().showEmail();
        if (list.contains(validatedEmail)) {
            System.out.println("i have this email");
        } else {
            newEmailOFExpert = validatedEmail;
        }

        String userName = "alfef526";

        String password = generateRandomPassword();

        LocalDate timeOfSignIn = LocalDate.now();

        Customer customer = new Customer(validatedFirstName, validatedLastName, newEmailOFExpert,
                userName, password, timeOfSignIn);

        ApplicationContext.getCustomerService().save(customer);
        System.out.println("customer added to data base !");
        customerAfterSignIn();
    }


    public static void customerAfterSignIn() {

        System.out.println("1. register order :");
        System.out.println("2.change password :");


        try {
            int select = scanner.nextInt();
            scanner.nextLine();
            switch (select) {
                case 1 -> registerOrder();

                case 2 -> changePassword();

                default -> System.out.println("---Error404---");
            }
        } catch (
                Exception e) {
            //     e.printStackTrace();
            System.out.println("!!!WRONG!!!");

        }
    }

    public static void registerOrder() throws SQLException {

        System.out.println("show service and subService :");
        ApplicationContext.getServiceService().findAll().forEach(System.out::println);
        System.out.println("---------------------------------------------------");
        ApplicationContext.getSubServiceService().findAll().forEach(System.out::println);
        registerCustomerOrderLevel2();

    }

    public static void registerCustomerOrderLevel2() throws SQLException {

        if (customerOptional.isPresent()) {
            CustomerOrder customerOrder = new CustomerOrder();
            Customer customer = ApplicationContext.getCustomerService().findById(648).orElse(null);
            SubService subService = ApplicationContext.getSubServiceService().findById(651).orElse(null);

            String descriptionOfOrder = "you should it ! ";

            Double proposedPrice = 300.00;

            Double fixPrice = ApplicationContext.getSubServiceService().priceOfSubService(651);
            Double validatedPrice = null;
            if (proposedPrice >= fixPrice) {
                validatedPrice = proposedPrice;
            } else {
                System.out.println("your price is under the lowest price");
            }

            String timeOfWork;
            timeOfWork = checkAndRegisterTimeOfLoan("1402/10/30");

            String address = "mashhad";

            StatusOfOrder waitingForSuggestExpert = StatusOfOrder.WAITING_FOR_SUGGEST_EXPERT;

            customerOrder.setCustomer(customer);
            customerOrder.setDescriptionOfOrder(descriptionOfOrder);
            customerOrder.setAddress(address);
            customerOrder.setStatusOfOrder(waitingForSuggestExpert);
            customerOrder.setProposedPrice(validatedPrice);
            customerOrder.setSubService(subService);
            customerOrder.setTimeOfDoing(timeOfWork);
            ApplicationContext.getCustomerOrderService().save(customerOrder);
            System.out.println("customer order saved to data base !");

            customerAfterSignIn();


        }
    }


    public static void changePassword() throws SQLException {
        ApplicationContext.getCustomerService().changePassword(648, "Frgw@2fw");
        System.out.println("password changed ! ");
        firstMenu();

    }

    public static String checkAndRegisterTimeOfLoan(String inputTime) throws SQLException {
        LocalDate currentTime = LocalDate.parse(inputTime, DateTimeFormatter.ISO_DATE);
        LocalDate firstStartDate = LocalDate.parse("1402-10-27", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        if (currentTime.isAfter(firstStartDate)) {
            inputTime = inputTime;
        } else {
            System.out.println("You should choose a date after 1402-10-27");
        }
        return inputTime;
    }


    /////////////////////////////////////////////////////////validations////////////////////////////////////////////


    public static String validationNames(String firstName) {
        boolean isTrue = true;
        while (isTrue) {
            if (validationName(firstName)) {
                isTrue = false;
            } else {
                System.out.println("Please enter a valid name!");
            }
        }
        return firstName;
    }

    public static String validationEmails(String email) {
        boolean isTrue = true;
        while (isTrue) {
            if (validateEmail(email)) {
                isTrue = false;
            } else {
                System.out.println("Please enter a valid email address!");
            }
        }
        return email;
    }
}

