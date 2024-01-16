
package menu;

import domain.userEntity.Customer;
import domain.userEntity.Expert;
import util.ApplicationContext;
import validation.RegexValidation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
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

        System.out.println("1. sign up :");
        System.out.println("2. sign in :");
        System.out.println("3.change password :");


        try {
            int select = scanner.nextInt();
            scanner.nextLine();
            switch (select) {
                case 1 -> signUpCustomer();

                case 2 -> signInCustomer();

                case 3->changePassword();

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

            String username ="alfef526";


            String password ="xxPr6v$6";

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
        System.out.println("2. sign in :");
        System.out.println("3.change password :");


        try {
            int select = scanner.nextInt();
            scanner.nextLine();
            switch (select) {
                case 1 -> registerOrder();

               // case 2 -> signInCustomer();

                case 3 -> changePassword();

                default -> System.out.println("---Error404---");
            }
        } catch (
                Exception e) {
       //     e.printStackTrace();
            System.out.println("!!!WRONG!!!");

        }
    }

    public static void registerOrder(){

        System.out.println("show service and subService :");
        ApplicationContext.getServiceService().findAll().forEach(System.out::println);
        System.out.println("---------------------------------------------------");
        ApplicationContext.getSubServiceService().findAll().forEach(System.out::println);

        customerAfterSignIn();



    }

    public static void changePassword() throws SQLException {
        ApplicationContext.getCustomerService().changePassword(641,"Frgw@2fw");
        System.out.println("password changed ! ");
        firstMenu();

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

