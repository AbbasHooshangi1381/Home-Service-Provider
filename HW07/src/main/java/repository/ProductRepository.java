package repository;

import role.Product;

import java.sql.*;

public class ProductRepository {

    private Connection connection;

    public ProductRepository(Connection connection) {
        this.connection=connection;
    }

    public ProductRepository() {

    }


    public int addProduct(Product product) throws SQLException {

        // ایجاد کوئری برای ذخیره اطلاعات محصول
        String sql = "INSERT INTO product (name,CreateDate ) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, product.getName());
        preparedStatement.setString(2, product.getCreateDate());

        // اجرای کوئری
        int resultAddProduct = preparedStatement.executeUpdate();
        return resultAddProduct;
    }

    public boolean categoryExists(int categoryId) throws SQLException {
        // ایجاد کوئری برای بررسی وجود دسته بندی
        String sql = "SELECT COUNT(*) FROM category WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, categoryId);

        // اجرای کوئری و بازگرداندن نتیجه
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        int count = resultSet.getInt(1);

        return count > 0;
    }


    public boolean brandExists(int brandId) throws SQLException {

        // ایجاد کوئری برای بررسی وجود دسته بندی
        String sql = "SELECT COUNT(*) FROM brand WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, brandId);

        // اجرای کوئری و بازگرداندن نتیجه
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        int count = resultSet.getInt(1);

        return count > 0;
    }


}
