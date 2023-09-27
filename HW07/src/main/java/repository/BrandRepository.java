package repository;

import role.Brand;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BrandRepository {

    private Connection connection;

    public BrandRepository(Connection connection) {
        this.connection = connection;
    }

    public BrandRepository() throws SQLException {

    }


    public int save(Brand brand) throws SQLException {
        String addBrand = "INSERT INTO brand(name, website, description) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(addBrand);
        preparedStatement.setString(1, brand.getName());
        preparedStatement.setString(2, brand.getWebsite());
        preparedStatement.setString(3, brand.getDescription());
        int result = preparedStatement.executeUpdate();
        return result;
    }

    public int updateNameBrand(String name, int id) throws SQLException {
        String query = "UPDATE brand SET name = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, id);
        int result = preparedStatement.executeUpdate();
        return result;
    }

    public int updateWebsiteBrand(String website, int id) throws SQLException {
        String query = "UPDATE brand SET website = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, website);
        preparedStatement.setInt(2, id);
        int result = preparedStatement.executeUpdate();
        return result;
    }

    public int updateDescriptionBrand(String description, int id) throws SQLException {
        String query = "UPDATE brand SET description = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, description);
        preparedStatement.setInt(2, id);
        int result = preparedStatement.executeUpdate();
        return result;
    }

    public int deleteBrand(int id) throws SQLException {
        String query = "DELETE FROM brand WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        int result = preparedStatement.executeUpdate();
        return result;
    }


}
