package service;

import repository.ProductRepository;
import role.Brand;
import role.Category;
import role.Product;

import java.sql.SQLException;

public class ProductService {

    private final ProductRepository productRepository=new ProductRepository();

    public ProductService() {
    }


public void registerProduct() throws SQLException {
    Product product=new Product(2,"ahmad","25/56");
    int resultProduct=productRepository.addProduct(product);

    if (resultProduct !=0){
        System.out.println(product.getName() + "successfully added to data base");
    }
    else
        System.out.println("wrong!");
}






}
