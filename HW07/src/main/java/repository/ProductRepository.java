package repository;

import role.Product;

import java.sql.*;

public class ProductRepository {

    private Connection connection;

    public ProductRepository(Connection connection) {
        this.connection=connection;
    }

    public ProductRepository() throws SQLException{

    }


    public int addProduct(Product product) throws SQLException {

        String sql = "INSERT INTO product (name,CreateDate ) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, product.getName());
        preparedStatement.setString(2, product.getCreateDate());


        int resultAddProduct = preparedStatement.executeUpdate();
        return resultAddProduct;
    }

    public boolean categoryExists(int categoryId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM category WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, categoryId);

        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        int count = resultSet.getInt(1);

        return count > 0;
    }


    public boolean brandExists(int brandId) throws SQLException {

        String sql = "SELECT COUNT(*) FROM brand WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, brandId);


        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        int count = resultSet.getInt(1);

        return count > 0;
    }

    public int deleteProduct(int id) throws SQLException {
        String query="DELETE FROM product WHERE id=?";
        PreparedStatement preparedStatement= connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        int result=preparedStatement.executeUpdate();
        return result;
    }


}
