package repository.UserRepositoryImpl;

import base.repository.impl.BaseRepositoryImpl;
import model.User;
import repository.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

public class UserRepositryImpl extends BaseRepositoryImpl<Integer, User> implements UserRepository {
    public UserRepositryImpl(Connection connection) {
        super(connection);
    }

    @Override
    public String getTableName() {
        return "Users";
    }

    @Override
    public String getColumnsName() {
        return "(firstname, lastname, username, password)";
    }

    @Override
    public String getUpdateQueryParams() {
        return "firstname=? , lastname=? , username=? , password=?";
    }

    @Override
    public String getCountOfQuestionMarkForParams() {
        return "(?, ?, ?, ?)";
    }

    @Override
    public User mapResultSetToEntity(ResultSet resultSet) throws SQLException {
            User customer = new User(
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getInt(6));
            customer.setId(resultSet.getInt(1)

            );
            return customer;
    }

    @Override
    public void fillParamForStatement(PreparedStatement preparedStatement, User entity, boolean isCountOnly)
            throws SQLException {
        preparedStatement.setString(1, entity.getFirstname());
        preparedStatement.setString(2, entity.getLastname());
        preparedStatement.setString(3, entity.getUsername());
        preparedStatement.setString(4, entity.getPassword());

    }


    @Override
    public User findByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM " + getTableName() + " WHERE username = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return mapResultSetToEntity(resultSet);

        }
        return null;
    }

    @Override
    public User login(String username) {
        return null;
    }


}
