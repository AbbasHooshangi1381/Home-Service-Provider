package repository;

import role.Brand;
import role.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BrandRepository {

    private Connection connection;

    public BrandRepository(Connection connection) {
        this.connection=connection;
    }

    public BrandRepository() {
    }

    public int save(Brand brand) throws SQLException {
        String addBrand = "INSERT INTO brand(name,website,description)VALUES(?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(addBrand);
        preparedStatement.setString(1, brand.getName());
        preparedStatement.setString(2, brand.getWebsite());
        preparedStatement.setString(3, brand.getDescription());
        int result = preparedStatement.executeUpdate();
        return result;
    }

    public int updateNameBrand(String name) throws SQLException {
        String query="UPDATE brand SET name = ? WHERE id=2";
        PreparedStatement preparedStatement= connection.prepareStatement(query);
        preparedStatement.setString(1,name);
        int result=preparedStatement.executeUpdate();
        return result;
    }
    public int updateWebsiteBrand(String website) throws SQLException {
        String query="UPDATE brand SET website = ? WHERE id=2";
        PreparedStatement preparedStatement= connection.prepareStatement(query);
        preparedStatement.setString(1,website);
        int result=preparedStatement.executeUpdate();
        return result;
    }
    public int updateDescriptionBrand(String description) throws SQLException {
        String query="UPDATE brand SET description = ? WHERE id=2";
        PreparedStatement preparedStatement= connection.prepareStatement(query);
        preparedStatement.setString(1,description);
        int result=preparedStatement.executeUpdate();
        return result;
    }


}
