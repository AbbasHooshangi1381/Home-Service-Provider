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

    public void register(User user) throws SQLException {
        int resault = userRepository.save(user);
        if (resault != 0)
            System.out.println(user.getFirstname() + "succesfully add to database");
        else
            System.out.println("oops!");
    }

    public User login(String username) throws SQLException {
        User user = userRepository.login(username);
        return user;


    }
    public void changeFirstname() throws SQLException {
        System.out.println(" enter new firstname");
        String firstname=input.nextLine();


    int result=userRepository.updateFirstname(firstname);
    if (result != 0)
            System.out.println("succesfully edited to database");
        else
                System.out.println("oops!");



}
public void delete() throws SQLException {
        int result=userRepository.delete(2);
    if (result != 0)
        System.out.println("succesfully deleted to database");
    else
        System.out.println("oops!");
}





}
