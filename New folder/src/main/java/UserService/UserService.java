package UserService;

import base.service.BaseService;
import model.Cart;
import model.Product;
import model.User;

import java.sql.SQLException;

public interface UserService extends BaseService<Integer, User> {

    User findByUsername(String username);


    void register(User user) throws SQLException;

    User  login(String username,String password) throws SQLException;

}
