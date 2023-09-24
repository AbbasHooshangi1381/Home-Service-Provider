package repository;

import com.sun.jdi.connect.spi.Connection;
import connection.JdbcConnection;
import role.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRepository {
    JdbcConnection jdbcConnection = new JdbcConnection();
    Connection connection = jdbcConnection.getConnection();

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
}
