package main;

import repository.UserRepository;
import role.User;
import service.UserService;
import util.AppllicationContext;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
     /*   UserRepository userRepository= AppllicationContext.getUserRepository();
        UserService userService= AppllicationContext.getUserService();*/

        UserService userService=new UserService();
        userService.changeName();
        userService.login();
        userService.register();

    }
}
