package repository;

import role.Brand;
import role.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CategoryRepository {


    private Connection connection;

    public CategoryRepository(Connection connection) {
        this.connection=connection;
    }

    public CategoryRepository() {
    }

    public int save(Category category) throws SQLException {
        String addCategory = "INSERT INTO category(name,description)VALUES(?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(addCategory);
        preparedStatement.setString(1, category.getName());
        preparedStatement.setString(2, category.getDescription());
        int result = preparedStatement.executeUpdate();
        return result;
    }
    public int updateNameCategory(String name) throws SQLException {
        String query="UPDATE category SET name = ? WHERE id=2";
        PreparedStatement preparedStatement= connection.prepareStatement(query);
        preparedStatement.setString(1,name);
        int result=preparedStatement.executeUpdate();
        return result;
    }
    public int updateDescription(String description) throws SQLException {
        String query="UPDATE category SET username = ? WHERE id=2";
        PreparedStatement preparedStatement= connection.prepareStatement(query);
        preparedStatement.setString(1,description);
        int result=preparedStatement.executeUpdate();
        return result;
    }

    public int deleteCategory(int id) throws SQLException {
        String query="DELETE FROM category WHERE id=?";
        PreparedStatement preparedStatement= connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        int result=preparedStatement.executeUpdate();
        return result;
    }




}
