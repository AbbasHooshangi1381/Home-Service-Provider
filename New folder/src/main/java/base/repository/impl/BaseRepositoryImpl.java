package base.repository.impl;

import base.model.BaseEntity;
import base.repository.BaseRepository;
import model.User;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseRepositoryImpl<ID extends Serializable,TYPE extends BaseEntity<ID>>
        implements BaseRepository<ID,TYPE> {

public final Connection connection;

    public BaseRepositoryImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public int save(TYPE entity) throws SQLException {
        String sql= "INSERT INTO " + getTableName()+" "+getColumnsName()+" VALUES"+getCountOfQuestionMarkForParams();
        PreparedStatement preparedStatement= connection.prepareStatement(sql);
        fillParamForStatement(preparedStatement,entity,false);

        preparedStatement.executeUpdate();

        return 0;
    }

    @Override
    public List<TYPE> findAll() throws SQLException {
        String sql = " SELECT * FROM " + getTableName();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            List<TYPE> entities = new ArrayList<>();
            while (resultSet.next()) {
                entities.add(mapResultSetToEntity(resultSet));
            }
            return entities;
        }
    }



    @Override
    public TYPE findById(ID id) throws SQLException {
        String sql = "SELECT * FROM " + getTableName() + " WHERE id = ? ;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, (Integer) id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
                return mapResultSetToEntity(resultSet);
        }
        return null;
    }

    @Override
    public void update(TYPE entity) throws SQLException {
        String sql = "UPDATE " + getTableName() + " SET " + getUpdateQueryParams() + " WHERE id = " + entity.getId();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            fillParamForStatement(statement, entity, true);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void delete(ID id) throws SQLException {
        String sql = "DELETE FROM " + getTableName() + " WHERE id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, (int) id);
            statement.executeUpdate();
        }
    }




    public abstract String getTableName() throws SQLException;

    public abstract String getColumnsName();

    public abstract String getUpdateQueryParams();

    public abstract String getCountOfQuestionMarkForParams();

    public abstract TYPE mapResultSetToEntity(ResultSet resultSet) throws SQLException;

    public abstract void fillParamForStatement(PreparedStatement preparedStatement,
                                               TYPE entity,
                                               boolean isCountOnly) throws SQLException;


    //public abstract void fillParamForStatement(PreparedStatement preparedStatement, User entity, boolean isCountOnly) throws SQLException;
}
