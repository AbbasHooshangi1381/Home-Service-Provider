package repository;


import connection.JdbcConnection;
import role.User;

import java.sql.*;

public class UserRepository {


    private Connection connection;

    public UserRepository(Connection connection) {
        this.connection=connection;
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


    public ResultSet ShowUsers() throws SQLException {
        String add = "SELECT * FROM user1 ";
        PreparedStatement preparedStatement = connection.prepareStatement(add);
        ResultSet arrays= preparedStatement.executeQuery();

        return arrays;
    }





    public User login(String username) throws SQLException {
        String loginQuery="SELECT * FROM user1 WHERE username=?";
        PreparedStatement preparedStatement=connection.prepareStatement(loginQuery);
        preparedStatement.setString(1,username);
        ResultSet resultSet= preparedStatement.executeQuery();
        if (resultSet.next()){
            User user= new User(
                    //resultSet.getInt("id"),
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
    public int updateName(String name,int id) throws SQLException {
        String query="UPDATE user1 SET name = ? WHERE id=?";
        PreparedStatement preparedStatement= connection.prepareStatement(query);
        preparedStatement.setString(1,name);
        preparedStatement.setInt(2,id);
        int result=preparedStatement.executeUpdate();
        return result;
    }
    public int updateUsername(String username,int id) throws SQLException {
        String query="UPDATE user1 SET username = ? WHERE id=?";
        PreparedStatement preparedStatement= connection.prepareStatement(query);
        preparedStatement.setString(1,username);
        preparedStatement.setInt(2,id);
        int result=preparedStatement.executeUpdate();
        return result;
    }
    public int updateEmail(String email,int id) throws SQLException {
        String query="UPDATE user1 SET email = ? WHERE id=?";
        PreparedStatement preparedStatement= connection.prepareStatement(query);
        preparedStatement.setString(1,email);
        preparedStatement.setInt(2,id);
        int result=preparedStatement.executeUpdate();
        return result;
    }
    public int updatePassword(String password,int id) throws SQLException {
        String query="UPDATE user1 SET password = ? WHERE id=?";
        PreparedStatement preparedStatement= connection.prepareStatement(query);
        preparedStatement.setString(1,password);
        preparedStatement.setInt(2,id);
        int result=preparedStatement.executeUpdate();
        return result;
    }

    public int deleteUsers(int id) throws SQLException {
        String query="DELETE FROM user1 WHERE id=?";
        PreparedStatement preparedStatement= connection.prepareStatement(query);
         preparedStatement.setInt(1,id);
        int result=preparedStatement.executeUpdate();
        return result;
    }







}
