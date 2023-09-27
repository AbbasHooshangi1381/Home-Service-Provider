package Menu;

import role.Brand;
import role.Category;
import role.User;
import service.BrandService;
import service.CategoryService;
import service.UserService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

@SuppressWarnings("unused")
public class Menu {

    private final Scanner scanner = new Scanner(System.in);
    private final UserService userService = new UserService();
    private final BrandService brandService = new BrandService();
    private final CategoryService categoryService = new CategoryService();

    public Menu() throws SQLException {
        this.publicMenu();
    }

    public void publicMenu() throws SQLException {
        System.out.println("---------------");
        System.out.println("((register-->1))");
        System.out.println("((login-->2))");
        System.out.println("((exit-->3))");
        int select = scanner.nextInt();
        scanner.nextLine();

        switch (select) {
            case 1:
                registerFromConsole();
                break;
            case 2:
                login();
                break;
            case 3:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Wrong option selected!");
                break;
        }
    }

    public void registerFromConsole() throws SQLException {
        System.out.println("Enter your name:");
        String name = scanner.nextLine();

        System.out.println("Enter your username:");
        String userName = scanner.nextLine();

        System.out.println("Enter your email:");
        String email = scanner.nextLine();

        System.out.println("Enter your password:");
        String password = scanner.nextLine();

        User user = new User(name, userName, email, password);
        userService.register(user);
    }

    public void login() throws SQLException {
        System.out.println("Enter your username:");
        String username = scanner.nextLine();

        System.out.println("Enter your password:");
        String password = scanner.nextLine();

        User user = userService.login(username);
        if (user == null || !user.getPassword().equals(password)) {
            System.out.println("Incorrect username or password");
        } else {
            boolean isTrue = true;
            while (isTrue) {
                System.out.println("------------------");
                System.out.println("1 - Create brand");
                System.out.println("2 - Create category");
                System.out.println("3 - Change email");
                System.out.println("4 - Change password");
                System.out.println("5 - Delete account");
                System.out.println("6 - Exit");

                int select = scanner.nextInt();
                scanner.nextLine();

                switch (select) {
                    case 1:
                     //   brandService.registerBrand();
                        break;
                    case 2:
                    //    categoryService.registerCategory();
                        break;
                    case 3:
                     //   userService.changeEmail();
                        break;
                    case 4:
                        userService.changePassword(user.getId());
                        break;
                    case 5:
                        userService.delete(user.getId());
                        break;
                    case 6:
                        isTrue = false;
                        break;
                    default:
                        System.out.println("Wrong option selected!");
                        break;
                }
            }
        }
    }
}