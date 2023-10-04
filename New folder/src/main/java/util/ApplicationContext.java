package util;

import UserService.UserService;
import UserService.impl.UserServiceImpl;
import connection.JdbcConnection;
import repository.UserRepository;
import repository.UserRepositoryImpl.UserRepositryImpl;

import java.sql.Connection;

public class ApplicationContext {

    public static Connection connection=JdbcConnection.getConnection();
    private static final UserRepository userRepository;
    private static final UserService userService;

    static {

        userRepository = new UserRepositryImpl(connection);
        userService = new UserServiceImpl(userRepository);
    }

    public static UserService getUserServiceImpel() {
        return userService;
    }
}
