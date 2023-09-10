package repository;

import connection.JdbcConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    JdbcConnection jdbcConnection = new JdbcConnection();
    Connection connection = jdbcConnection.getConnection();

    public UserRepository() throws SQLException {
    }

    public int save(User user) throws SQLException {

        String add = "INSERT INTO users(firstname,lastname,username,password)VALUES(?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(add);
        preparedStatement.setString(1, user.getFirstname());
        preparedStatement.setString(2, user.getLastname());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.setString(4, user.getUsername());
        int resault = preparedStatement.executeUpdate();
        return resault;
    }

    public User login(String username) throws SQLException {
        String loginQuery = "SELECT * FROM username WHERE username=?";
        PreparedStatement preparedStatement = connection.prepareStatement(loginQuery);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            User user= new User(
                    resultSet.getInt("id"),
            resultSet.getString("firstname"),
            resultSet.getString("username"),
            resultSet.getString("password"),
            resultSet.getString("lastname")
       );
            return user;


        }
        else
            return null;

    }

}
