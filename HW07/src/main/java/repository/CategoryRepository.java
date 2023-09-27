package repository;

import role.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CategoryRepository {

    private Connection connection;

    public CategoryRepository(Connection connection) {
        this.connection = connection;
    }

    public CategoryRepository() throws SQLException{

    }


    public int save(Connection connection, Category category) throws SQLException {
        String addCategory = "INSERT INTO category(name, description) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(addCategory);
        preparedStatement.setString(1, category.getName());
        preparedStatement.setString(2, category.getDescription());
        int result = preparedStatement.executeUpdate();
        return result;
    }

    public int updateNameCategory(Connection connection, String name) throws SQLException {
        String query = "UPDATE category SET name = ? WHERE id = 2";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, name);
        int result = preparedStatement.executeUpdate();
        return result;
    }

    public int updateDescription(Connection connection, String description) throws SQLException {
        String query = "UPDATE category SET description = ? WHERE id = 2";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, description);
        int result = preparedStatement.executeUpdate();
        return result;
    }

    public int deleteCategory(Connection connection, int id) throws SQLException {
        String query = "DELETE FROM category WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        int result = preparedStatement.executeUpdate();
        return result;
    }
}
