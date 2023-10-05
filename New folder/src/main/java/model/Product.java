package model;

import base.model.BaseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Product extends BaseEntity<Integer> {

    private int id;
    private String name;
    private double price;
    private Integer stock;

    public Product(int id, String name, Double price, Integer stock) {
        super(stock);
        //super(id, name, price, stock);
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Integer getStock() {
        return stock;
    }

    public static List<Product> getProductList() {
        return productList;
    }


    public static Product getProductData(ProductsEn productsEn) {
        switch (productsEn) {
            case Phone : return new Product(1, "iPhoneX 2018", 2000.00, 10);
            case Laptop : return new Product(2, "Laptop 2018", 27000.00, 8);
            case tv : return new Product(3, "LG", 100.000, 2);
            case WomenShoes : return new Product(4, "WomenShoes 2018", 8.000, 4);
            case MeanShoes : return new Product(5, "MenShoes 2018", 5.000, 6);
        }

        return null;
    }

   public static List<Product> productList = new ArrayList<>();

static {
    productList.add(

            getProductData(ProductsEn.Phone));
    productList.add(

            getProductData(ProductsEn.Laptop));
    productList.add(

            getProductData(ProductsEn.tv));
    productList.add(

            getProductData(ProductsEn.WomenShoes));
    productList.add(

            getProductData(ProductsEn.MeanShoes));

}
    public static void deleteProductById(List<Product> productList, int productId) {
        for (Product product : productList) {
            if (product.getId() == productId) {
                productList.remove(product);
                System.out.println("Product with ID " + productId + " has been deleted.");
                return;
            }
        }

        System.out.println("Product with ID " + productId + " not found in the list.");
    }

    public static void addToCart(Product product, List<Product> cart , int stock) {
    if(product.getStock() >= stock){
        cart.add(product);
        int count = Collections.frequency(cart, product);
        System.out.println(product.getName() + " has been added to the cart. Total count: " + count);
    }

    }


        public enum ProductsEn {
            MeanShoes,
            WomenShoes,
            tv,
            Laptop,
            Phone,
        }



}
