package menu;

import model.User;
import service.UserService;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    private final Scanner input = new Scanner(System.in);
    private final UserService userService = new UserService();

    public Menu() throws SQLException {
    }

    public void PublicMenu() throws SQLException {
        System.out.println("***welcome*** ");
        System.out.println("1---sigh up");
        System.out.println("2---sigh out");
        System.out.println("3---sigh in");
        System.out.println(" enter your select");
        int select = input.nextInt();
        input.nextLine();
        switch (select) {
            case 1 -> register();
            case 2 -> System.out.println("exit");
            case 3 -> enter();
            case 4 -> System.out.println("مریضی؟");
        }
    }

    public  void register() throws SQLException {
        System.out.println("enter your firstname");
        String firstname = input.nextLine();

        System.out.println("enter your lastname");
        String lastname = input.nextLine();

        System.out.println("enter your username");
        String username = input.nextLine();

        System.out.println("enter your password");
        String password = input.nextLine();

        User user = new User(null, firstname, lastname, username, password);
        userService.register(user);

    }

    public void enter() throws SQLException {
        System.out.println("enter your username");
        String username = input.nextLine();

        System.out.println("enter your password");
        String password = input.nextLine();
        User user=userService.login(username);
        if (user==null && !user.getPassword().equals(password))
            System.out.println("user name wrong");
        else
            System.out.println("cooroct");

    }

}
