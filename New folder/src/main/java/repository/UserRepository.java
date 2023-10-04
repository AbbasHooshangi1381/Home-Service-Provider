package repository;

import base.repository.BaseRepository;
import model.Product;
import model.User;

import java.sql.SQLException;

public interface UserRepository extends BaseRepository <Integer, User>{
    User findByUsername(String username) throws SQLException;

    User login(String username);
}
