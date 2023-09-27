package repository;

import role.Brand;
import role.ShareHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ShareHolderRepository {
    private Connection connection;

    public ShareHolderRepository(Connection connection) {
        this.connection=connection;
    }

    public ShareHolderRepository() throws SQLException {

    }


    public int save(ShareHolder shareHolder) throws SQLException {
        String addShareHolder = "INSERT INTO shareholder(name,phoneNumber,nationalCode)VALUES(?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(addShareHolder);
        preparedStatement.setString(1, shareHolder.getName());
        preparedStatement.setString(2, shareHolder.getPhoneNumber());
        preparedStatement.setString(3, shareHolder.getNationalCode());
        int result = preparedStatement.executeUpdate();
        return result;
    }

    public int updateNameshareHolder(String name) throws SQLException {
        String query="UPDATE shareholder SET name = ? WHERE id=2";
        PreparedStatement preparedStatement= connection.prepareStatement(query);
        preparedStatement.setString(1,name);
        int result=preparedStatement.executeUpdate();
        return result;
    }
    public int updatePhoneNumber(String phoneNumber) throws SQLException {
        String query="UPDATE shareholder SET phonenumber = ? WHERE id=2";
        PreparedStatement preparedStatement= connection.prepareStatement(query);
        preparedStatement.setString(1,phoneNumber);
        int result=preparedStatement.executeUpdate();
        return result;
    }
    public int updatenationalCode(String nationalCode) throws SQLException {
        String query="UPDATE shareholder SET nationalCode = ? WHERE id=2";
        PreparedStatement preparedStatement= connection.prepareStatement(query);
        preparedStatement.setString(1,nationalCode);
        int result=preparedStatement.executeUpdate();
        return result;
    }

    public int deleteShareHolder(int id) throws SQLException {
        String query="DELETE FROM shareholder WHERE id=?";
        PreparedStatement preparedStatement= connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        int result=preparedStatement.executeUpdate();
        return result;
    }




}
