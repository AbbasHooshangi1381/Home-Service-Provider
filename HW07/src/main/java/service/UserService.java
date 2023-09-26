package service;

import repository.UserRepository;
import role.User;

import java.sql.SQLException;
import java.util.Scanner;

public class UserService {
    private final UserRepository userRepository = new UserRepository();
    Scanner input = new Scanner(System.in);

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

        UserRepository userRepository = new UserRepository();
        User user = userRepository.login(username);
    if ((user != null)  && user.getPassword().equals(password) ){
        System.out.println(" login successfully ");
    }
else
        System.out.println("wrong!");
}
public void changeName() throws SQLException {
    System.out.println("put your new name");
    String name=input.nextLine();
    int result=userRepository.updateName(name);
    if (result != 0) {
        System.out.println( "successfully edited to data base");
    } else
        System.out.println(" Oops!");
}
    public void changeUsername() throws SQLException {
        System.out.println("put your new username");
        String username=input.nextLine();
        int result=userRepository.updateName(username);
        if (result != 0) {
            System.out.println( "successfully edited username to data base");
        } else
            System.out.println(" Oops! username");
    }
    public void changeEmail() throws SQLException {
        System.out.println("put your new email");
        String email=input.nextLine();
        int result=userRepository.updateName(email);
        if (result != 0) {
            System.out.println( "successfully edited email to data base");
        } else
            System.out.println(" Oops! email");
    }
    public void changePassword() throws SQLException {
        System.out.println("put your new password");
        String password=input.nextLine();
        int result=userRepository.updateName(password);
        if (result != 0) {
            System.out.println( "successfully edited password to data base");
        } else
            System.out.println(" Oops! password ");
    }

    public void delete() throws SQLException {
        int result=userRepository.deleteUsers( 2);
        if (result !=0){
            System.out.println("successfully deleted");
        }
        else
            System.out.println("not deleted !");
    }


}

