package UserService.impl;

import UserService.UserService;
import base.service.impl.BaseServiceImpl;
import connection.JdbcConnection;
import model.Product;
import model.User;
import repository.UserRepository;

import java.sql.Connection;
import java.sql.SQLException;



public class UserServiceImpl extends BaseServiceImpl<Integer, User, UserRepository>
        implements UserService {
    private Connection connection= JdbcConnection.getConnection();
    UserRepository repository;


    public UserServiceImpl(UserRepository repository) {

        super(repository);
        this.repository = repository;
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public void register(User user) throws SQLException {
        int result = repository.save(user);

        if (result != 0) {
            System.out.println(user.getFirstname() + "successfully added to data base");
        } else
            System.out.println(" wrong!");
    }



    @Override
    public User login(String username) {
        User user = repository.login(username);
        return user;    }
}


