package service;

import repository.UserRepository;
import role.User;

import java.sql.SQLException;

public class UserService {
    private final UserRepository userRepository=new UserRepository();

    public UserService() throws SQLException {
    }

    public void register() throws SQLException {
        User user=new User(2,"abbas","abbas1381","basjfryan@gmail.com","aassdd");
        int result=userRepository.save(user);

        if (result !=0){
            System.out.println(user.getName()+"successfully added to data base");
        }
        else
            System.out.println(" wrong!");
    }



}
