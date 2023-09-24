package main;

import repository.UserRepository;
import role.User;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserRepository userRepository=new UserRepository();
        User user=new User(2,"abbas","abbas1381","basjfryan@gmail.com","aassdd");
        userRepository.save(user);
    }
}
