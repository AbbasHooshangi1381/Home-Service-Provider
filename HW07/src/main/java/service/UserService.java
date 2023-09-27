package service;

import connection.JdbcConnection;
import repository.UserRepository;
import role.User;

import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class    UserService {
    private Connection connection= JdbcConnection.getConnection();
    private final UserRepository userRepository = new UserRepository(connection);
    Scanner input = new Scanner(System.in);




    public void register(User user) throws SQLException {

        int result = userRepository.save(user);

        if (result != 0) {
            System.out.println(user.getName() + "successfully added to data base");
        } else
            System.out.println(" wrong!");
    }

    public User login(String username) throws SQLException {
        UserRepository userRepository = new UserRepository(connection);
        User user = userRepository.login(username);
        return user;


}

    public ResultSet ShowUsers() throws SQLException {
        UserRepository userRepository = new UserRepository(connection);
        ResultSet array = userRepository.ShowUsers();
        return array;


    }



public void changeName(int id) throws SQLException {
    System.out.println("put your new name");
    String name=input.nextLine();
    int result=userRepository.updateName(name,id);
    if (result != 0) {
        System.out.println( "successfully edited to data base");
    } else
        System.out.println(" Oops!");
}


    public void changeUsername(int id) throws SQLException {
        System.out.println("put your new username");
        String username=input.nextLine();
        int result=userRepository.updateName(username,id);
        if (result != 0) {
            System.out.println( "successfully edited username to data base");
        } else
            System.out.println(" Oops! username");
    }
    public void changeEmail(int id) throws SQLException {
        System.out.println("put your new email");
        String email=input.nextLine();
        int result=userRepository.updateName(email,id);
        if (result != 0) {
            System.out.println( "successfully edited email to data base");
        } else
            System.out.println(" Oops! email");
    }
    public void changePassword(int id) throws SQLException {
        System.out.println("put your new password");
        String password=input.nextLine();
        int result=userRepository.updateName(password,id);
        if (result != 0) {
            System.out.println( "successfully edited password to data base");
        } else
            System.out.println(" Oops! password ");
    }


    public void delete(int id) throws SQLException {
        int result=userRepository.deleteUsers(id);
        if (result !=0){
            System.out.println("successfully deleted");
        }
        else
            System.out.println("not deleted !");
    }


}

