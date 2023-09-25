package repository;


import connection.JdbcConnection;
import role.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {


    private Connection connection;

    public UserRepository(Connection connection) {
        this.connection=connection;
    }

    public UserRepository() {

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
    public User login(String username) throws SQLException {
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
    public int updateName(String name) throws SQLException {
        String query="UPDATE user1 SET name = ? WHERE id=2";
        PreparedStatement preparedStatement= connection.prepareStatement(query);
        preparedStatement.setString(1,name);
        int result=preparedStatement.executeUpdate();
        return result;
    }
    public int updateUsername(String username) throws SQLException {
        String query="UPDATE user1 SET username = ? WHERE id=2";
        PreparedStatement preparedStatement= connection.prepareStatement(query);
        preparedStatement.setString(1,username);
        int result=preparedStatement.executeUpdate();
        return result;
    }
    public int updateEmail(String email) throws SQLException {
        String query="UPDATE user1 SET email = ? WHERE id=2";
        PreparedStatement preparedStatement= connection.prepareStatement(query);
        preparedStatement.setString(1,email);
        int result=preparedStatement.executeUpdate();
        return result;
    }
    public int updatePassword(String password) throws SQLException {
        String query="UPDATE user1 SET password = ? WHERE id=2";
        PreparedStatement preparedStatement= connection.prepareStatement(query);
        preparedStatement.setString(1,password);
        int result=preparedStatement.executeUpdate();
        return result;
    }







}
