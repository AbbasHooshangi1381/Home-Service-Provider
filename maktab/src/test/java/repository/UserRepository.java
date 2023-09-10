package repository;

import connection.JdbcConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRepository{
JdbcConnection jdbcConnection=new JdbcConnection();
Connection connection= jdbcConnection.getConnection();

    public UserRepository() throws SQLException {
    }

    public int save(User user) throws SQLException {

        String add = "INSERT INTO users(firstname,lastname,username,password)VALUES(?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(add);
        preparedStatement.setString(1, user.getFirstname());
        preparedStatement.setString(2,user.getLastname());
        preparedStatement.setString(3,user.getPassword());
        preparedStatement.setString(4,user.getUsername());
        int resault = preparedStatement.executeUpdate();
        return resault;
    }


}
