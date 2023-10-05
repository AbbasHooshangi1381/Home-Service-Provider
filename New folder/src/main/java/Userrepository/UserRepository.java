package Userrepository;

import base.repository.BaseRepository;
import model.User;

import java.sql.SQLException;

public interface UserRepository extends BaseRepository <Integer, User>{
    User findByUsername(String username) throws SQLException;

    User login(String username,String password) throws SQLException;
}
