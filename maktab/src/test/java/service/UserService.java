package service;

import model.User;
import repository.UserRepository;

import java.sql.SQLException;
import java.util.Scanner;

public class UserService {
    private final UserRepository userRepository = new UserRepository();
    Scanner input = new Scanner(System.in);
    public UserService() throws SQLException {
    }

    public void register() throws SQLException {

        User user = new User(null, "abbas", "hooshangi", "abbasd", "assdfs");
        int resault = userRepository.save(user);
        if (resault != 0)
            System.out.println(user.getFirstname() + "succesfully add to database");
        else
            System.out.println("oops!");
    }

    public void login() throws SQLException {

        System.out.println(" please enter your username");
        String username = input.nextLine();
        System.out.println(" please enter your password");
        String password = input.nextLine();
        User user = userRepository.login(username);
        if ((user != null) && user.getPassword().equals(password))
            System.out.println("login successfully");
        else
            System.out.println("bad code");


    }
    public void changeFirstname() throws SQLException {
        System.out.println(" enter new firstname");
        String firstname=input.nextLine();


    int result=userRepository.updateFirstname(firstname);
    if (result != 0)
            System.out.println("succesfully edited to database");
        else
                System.out.println("oops!");



}}
