package util;

import UserService.UserService;
import UserService.impl.UserServiceImpl;
import cart.repository.CartRepository;
import cart.repository.impl.CartRepositoryImpl;
import cart.service.CartService;
import cart.service.cartServiceImpl.CartServiceImpl;
import connection.JdbcConnection;
import menu.Menu;
import Userrepository.UserRepository;
import Userrepository.UserRepositoryImpl.UserRepositryImpl;

import java.sql.Connection;

public class ApplicationContext {

    public static Connection connection=JdbcConnection.getConnection();
    private static final UserRepository userRepository;
    private static final CartRepository cartRepostitory;
    private static final UserService userService;
    private static final CartService cartService;
    private static Menu menu;

    static {

        userRepository = new UserRepositryImpl(connection);
        cartRepostitory = new CartRepositoryImpl(connection);
        userService = new UserServiceImpl(userRepository);
        cartService=new CartServiceImpl(cartRepostitory) {
        };
        try {
            menu=new Menu();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static UserService getUserServiceImpel() {
        return userService;
    }
    public static CartService getCartService(){
        return cartService;
    }
}
