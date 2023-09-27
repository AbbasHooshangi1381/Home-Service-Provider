package service;

import connection.JdbcConnection;
import repository.ProductRepository;
import role.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class ProductService {
    private Connection connection = JdbcConnection.getConnection();
    private final ProductRepository productRepository = new ProductRepository(connection);
    Scanner input = new Scanner(System.in);

    public void registerProduct() throws SQLException {
        Product product = new Product(2, "ahmad", "25/56");
        int resultProduct = productRepository.addProduct(product);

        if (resultProduct > 0) {
            System.out.println(product.getName() + "successfully added to database");
        } else {
            System.out.println("wrong!");
        }
    }
/*
public void categoryExists() throws SQLException{
    ProductService productService = new ProductService();

if (productService.categoryExists()) {
        System.out.println("Category exists!");
    } else {
        System.out.println("Category does not exist!");
    }
}
*/



    public void delete() throws SQLException {
        int result = productRepository.deleteProduct(2);
        if (result > 0) {
            System.out.println("successfully deleted");
        } else {
            System.out.println("not deleted!");
        }
    }
}
