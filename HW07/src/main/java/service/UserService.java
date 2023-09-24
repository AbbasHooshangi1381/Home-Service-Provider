package service;

import repository.UserRepository;
import role.User;

import java.sql.SQLException;
import java.util.Scanner;

public class UserService {
    private final UserRepository userRepository = new UserRepository();

    public UserService() throws SQLException {
    }

    public void register() throws SQLException {
        User user = new User(2, "abbas", "abbas1381", "basjfryan@gmail.com", "aassdd");
        int result = userRepository.save(user);

        if (result != 0) {
            System.out.println(user.getName() + "successfully added to data base");
        } else
            System.out.println(" wrong!");
    }

    public void login() throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("put your username");
        String username = input.nextLine();
        System.out.println("enter your password");
        String password = input.nextLine();


    User user = UserRepository.login(username);
    if ((user != null)  && user.getPassword().equals(password) ){
        System.out.println(" login successfully ");
    }
else
        System.out.println("wrong!");
}
}
