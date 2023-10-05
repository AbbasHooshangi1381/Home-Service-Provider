
package util;

import UserService.UserService;
import menu.Menu;
import model.User;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        Menu menu=new Menu();
      UserService userService=ApplicationContext.getUserServiceImpel();
        //add
        User user=new User("tahere","ahmadi","asddsa","dddccc",4);
        userService.save(user);

        //All users
        List<User> all = userService.findAll();
        System.out.println(all.size());

        //update

        user.setId(2);
        user.setFirstname("javad");
        user.setLastname("nazeri");
        user.setUsername("pppppppppp");
        user.setPassword("as'bjhjf");

        //delete
        userService.delete(2);



    }
}

