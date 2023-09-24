package repository;

import com.sun.jdi.connect.spi.Connection;
import connection.JdbcConnection;
import role.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    JdbcConnection jdbcConnection = new JdbcConnection();
    static Connection connection = jdbcConnection.getConnection();

    public UserRepository() throws SQLException {
    }

    public int save(User user) throws SQLException {
        String add = "INSERT INTO user1(name,username,email,password)VALUES(?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(add);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getUsername());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getPassword());
        int result = preparedStatement.executeUpdate();
        return result;
    }
    public static User login(String username) throws SQLException {
        String loginQuery="SELECT * FROM user1 WHERE username=?";
        PreparedStatement preparedStatement=connection.prepareStatement(loginQuery);
        preparedStatement.setString(1,username);
        ResultSet resultSet= preparedStatement.executeQuery();
        if (resultSet.next()){
            User user= new User(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("username"),
                    resultSet.getString("email"),
                    resultSet.getString("password")
            );
            return user;
        }
        else
            return null;

    }







}
