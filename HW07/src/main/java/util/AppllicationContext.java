package util;

import Menu.Menu;
import connection.JdbcConnection;
import repository.*;
import service.*;

import java.sql.Connection;
import java.sql.SQLException;

 public  class  AppllicationContext {
    private static UserService userService;
    private static UserRepository userRepository;
    private static ShareHolderRepository shareHolderRepository;
    private static ShareHolderService shareHolderService;
    private static ProductRepository productRepository;
    private static ProductService productService;
    private static CategoryRepository categoryRepository;
    private static CategoryService categoryService;
    private static BrandRepository brandRepository;
    private static BrandService brandService;
    private static  Menu menu;
    public static Connection connection=JdbcConnection.getConnection();



    public static Menu menu() throws SQLException {
        if (menu==null){
            menu=new Menu();
        }
        return menu;
    }

    public static UserRepository getUserRepository() {
        if (userRepository==null){
            userRepository=new UserRepository(connection);
        }
        return userRepository;
    }

    public static UserService getUserService() throws SQLException {
        if (userService==null){
            userService=new UserService();
        }
        return userService;
    }

    public static ShareHolderRepository getShareHolderRepository() {
        if (shareHolderRepository==null){
            shareHolderRepository=new ShareHolderRepository();
        }
        return shareHolderRepository;
    }

    public static ShareHolderService getShareHolderService() throws SQLException {
        if (shareHolderService==null){
            shareHolderService=new ShareHolderService(
            );
        }
        return shareHolderService;
    }

    public static ProductRepository getProductRepository() {
        if (productRepository==null){
            productRepository=new ProductRepository();
        }
        return productRepository;
    }

    public static ProductService getProductService() {
        if (productService==null){
            productService=new ProductService();
        }
        return productService;
    }

    public static CategoryRepository getCategoryRepository() {
        if (categoryRepository==null){
            categoryRepository=new CategoryRepository();
        }
        return categoryRepository;
    }

    public static CategoryService getCategoryService() {
        if (categoryService==null){
            categoryService=new CategoryService();
        }
        return categoryService;
    }

    public static BrandRepository getBrandRepository() {
        if (brandRepository==null){
            brandRepository=new BrandRepository();
        }
        return brandRepository;
    }

    public static BrandService getBrandService() throws SQLException {
        if (brandService==null){
            brandService=new BrandService();
        }
        return brandService;
    }




}
