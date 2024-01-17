
package menu;

import domain.userEntity.Admin;
import domain.userEntity.Customer;
import domain.userEntity.Expert;
import util.ApplicationContext;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static menu.AdminMenu.firstMenuOfAdmin;
import static menu.AdminMenu.signInOrSignUpAdmin;
import static menu.CustomerMenu.*;
import static menu.ExpertMenu.signInOrSignUpExpert;
import static validation.Validation.generateRandomPassword;

public class BaseMenu {
    public static final Scanner scanner = new Scanner(System.in);

    public static void firstMenu() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n*************************************************************");
        System.out.println("|                  WELCOME TO FINAL-PROJECT                   |");
        System.out.println("*************************************************************\n");
        System.out.println("1- admin");
        System.out.println("2- Customer");
        System.out.println("3- Expert");
        System.out.println("4- Exit\n");
        System.out.println("Enter your select:");

        try {
            int select = scanner.nextInt();
            scanner.nextLine();
            switch (select) {
                case 1 -> signInOrSignUpAdmin();
                case 2 -> signInOrSignUpCustomer();
                case 3 -> signInOrSignUpExpert();
                case 4 -> {
                    System.out.println("exit");
                }
                default -> System.out.println("---Eror404---");
            }
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("!!!WRONG!!!");
            firstMenu();
        }


}







}

