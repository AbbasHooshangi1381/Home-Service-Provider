package cart.service.cartServiceImpl;

import base.service.impl.BaseServiceImpl;
import cart.repository.CartRepository;
import cart.service.CartService;
import connection.JdbcConnection;
import model.Cart;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CartServiceImpl extends BaseServiceImpl<Integer, Cart , CartRepository> implements CartService {
    private Connection connection= JdbcConnection.getConnection();
    CartRepository repository;

    public CartServiceImpl(CartRepository repository) {
        super(repository);
    }


    @Override
    public void save(Cart entity) throws SQLException {
        repository.save(entity);
        return;
    }

    @Override
    public List<Cart> findAll() throws SQLException {
        return null;
    }

    @Override
    public Cart findById(Integer integer) throws SQLException {
        return null;
    }

    @Override
    public void update(Cart entity) throws SQLException {

    }

    @Override
    public void delete(Integer integer) throws SQLException {

    }
}
