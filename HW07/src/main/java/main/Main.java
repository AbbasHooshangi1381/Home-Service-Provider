package main;

import repository.UserRepository;
import role.User;
import service.UserService;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService=new UserService();
        userService.register();

        userService.login();

    }
}
