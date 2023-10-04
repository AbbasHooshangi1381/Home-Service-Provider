package menu;

import UserService.UserService;
import model.Cart;
import model.Product;
 import model.User;
import util.ApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static model.Product.deleteProductById;
import static model.Product.productList;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    UserService userService = ApplicationContext.getUserServiceImpel();

    public Menu() throws SQLException {
        this.publicMenu();
    }

    public void publicMenu() throws SQLException {
        System.out.println("---------------");
        System.out.println("((register-->1))");
        System.out.println("((login-->2))");
        System.out.println("((exit-->3))");
        System.out.println("---------------");

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
        System.out.println("Enter your firstname:");
        String firstname = scanner.nextLine();

        System.out.println("Enter your lastname:");
        String lastname = scanner.nextLine();

        System.out.println("Enter your username:");
        String username = scanner.nextLine();

        System.out.println("Enter your password:");
        String password = scanner.nextLine();

        User user = new User(firstname, lastname, username, password, 0);
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
                System.out.println("1 - show cart");
                System.out.println("2 - add cart");
                System.out.println("3 - delete cart");
                System.out.println("4 - all added product to cart");
                System.out.println("5 - sum of the cart");
                System.out.println("6 - Exit");
                System.out.println("------------------");

                int select = scanner.nextInt();
                scanner.nextLine();

                switch (select) {
                    case 1:

                        //It should show all the product that added to cart with count of it
                        // it is false....
                        Cart cart = new Cart(2, "iphone", 800.0, 5,
                                9, 5, 75);
                        break;
                    case 2:

                        // in this method we should add to cart by Considering to count of it

                        System.out.println("1- iPhoneX 2018 28.000.000 TM");
                        System.out.println("2- Asus  50.000.000 TM");
                        System.out.println("3- TV 7.000.000 TM");
                        System.out.println("4- menShoes 7.000 TM");
                        System.out.println("5- womenShoes 10.000 TM");

                        int select_product = scanner.nextInt();
                        scanner.nextLine();
                        Product.ProductsEn productsEn = null;

                        switch (select_product) {
                            case 1:
                                productsEn = Product.ProductsEn.Phone;
                                break;
                            case 2:
                                productsEn = Product.ProductsEn.Laptop;
                                break;
                            case 3:
                                productsEn = Product.ProductsEn.tv;
                                break;
                            case 4:
                                productsEn = Product.ProductsEn.MeanShoes;
                                break;

                            case 5:
                                productsEn = Product.ProductsEn.WomenShoes;
                                break;
                        }

                        Product product = Product.getProductData(productsEn);
                        System.out.println(product);


                    case 3:
                        // Delete item from cart
                        System.out.println("Enter the product ID to delete:");
                        System.out.println("1- iPhoneX ");
                        System.out.println("2- Asus");
                        System.out.println("3- TV ");
                        System.out.println("4- menShoes");
                        System.out.println("5- womenShoes");


                        int productId = scanner.nextInt();
                        scanner.nextLine();
                        Product.deleteProductById(Product.productList, productId);

                        switch (productId) {
                            case 1:
                                if (productId == 1) {
                                    Product.deleteProductById(Product.productList, 1);
                                }
                                break;

                            case 2:
                                if (productId == 2) {
                                    Product.deleteProductById(Product.productList, 2);
                                }
                                break;

                            case 3:
                                if (productId == 3) {
                                    Product.deleteProductById(Product.productList, 3);
                                }
                                break;

                            case 4:
                                if (productId == 4) {
                                    Product.deleteProductById(Product.productList, 4);
                                }
                                break;


                            case 5:
                                if (productId == 5) {
                                    Product.deleteProductById(Product.productList, 5);
                                }
                                break;

                            //now we should show remain list

                            default:
                                System.out.println("Wrong option selected!");
                                break;
                        }

                    case 4:

                        //this method is not complete because it is just show tv add

                        Product tv = Product.getProductData(Product.ProductsEn.tv);
                        List<Product> cart1 = new ArrayList<>();
                        Product.addToCart(tv, cart1);
                        break;

                    case 5:

                        //it should be complete and where i should write getter for name and price
                        // to use it in sum of price and added product method



                    case 6: System.out.println("bye without hand...");
                }
            }
        }
    }
}



 
