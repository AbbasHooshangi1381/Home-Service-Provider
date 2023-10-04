package cart.repository.impl;

import base.repository.impl.BaseRepositoryImpl;
import cart.repository.CartRepository;
import model.Cart;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CartRepositoryImpl extends BaseRepositoryImpl<Integer, Cart> implements CartRepository {
    public CartRepositoryImpl(Connection connection) {
        super(connection);
    }

    @Override
    public String getTableName()  {
        return "cart";

    }

    @Override
    public String getColumnsName() {
        return "(id , product_id , count , user_id)";
    }

    @Override
    public String getUpdateQueryParams() {
        return "(id=? , product_id=? , count=? , user_id=?)";
    }

    @Override
    public String getCountOfQuestionMarkForParams() {
        return "(?,?,?,?)";
    }

    @Override
    public Cart mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        Cart customer = new Cart(
                resultSet.getInt(2),
                resultSet.getString(3),
                resultSet.getDouble(4),
                resultSet.getInt(5),
                resultSet.getInt(6),
                resultSet.getInt(7),
                resultSet.getInt(8));
                customer.setId(resultSet.getInt(1));
                return customer;

    }

    @Override
    public void fillParamForStatement(PreparedStatement preparedStatement, Cart entity, boolean isCountOnly)
            throws SQLException {
        preparedStatement.setInt(1, entity.getId());
        preparedStatement.setInt(2, entity.getCart_id());
        preparedStatement.setInt(3, entity.getProduct_id());
        preparedStatement.setInt(4, entity.getUser_id());

    }

}
