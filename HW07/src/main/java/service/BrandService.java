package service;

import connection.JdbcConnection;
import repository.BrandRepository;
import repository.UserRepository;
import role.Brand;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class BrandService {
    private Connection connection= JdbcConnection.getConnection();
    private final BrandRepository brandRepository = new BrandRepository(connection);
    private Scanner input = new Scanner(System.in);


    public void registerBrand(Brand brand) throws SQLException {
        int result = brandRepository.save(brand);

        if (result != 0) {
            System.out.println(brand.getName() + " successfully added to the database");
        } else {
            System.out.println("Error while adding brand to the database!");
        }
    }

    public void changeName(int id) throws SQLException {
        System.out.println("Enter the new name:");
        String name = input.nextLine();

        int result = brandRepository.updateNameBrand(name, id);
        if (result != 0) {
            System.out.println("Brand name successfully updated in the database");
        } else {
            System.out.println("Error while updating brand name in the database!");
        }
    }

    public void changeWebsite(int id) throws SQLException {
        System.out.println("Enter the new website:");
        String website = input.nextLine();

        int result = brandRepository.updateWebsiteBrand(website, id);
        if (result != 0) {
            System.out.println("Brand website successfully updated in the database");
        } else {
            System.out.println("Error while updating brand website in the database!");
        }
    }

  /*  public void changeEmail(int id) throws SQLException {
        System.out.println("Enter the new email:");
        String email = input.nextLine();

        int result = brandRepository.updateEmailBrand(email, id);
        if (result != 0) {
            System.out.println("Brand email successfully updated in the database");
        } else {
            System.out.println("Error while updating brand email in the database!");
        }
    }*/

    public void delete(int id) throws SQLException {
        int result = brandRepository.deleteBrand(id);
        if (result != 0) {
            System.out.println("Brand successfully deleted from the database");
        } else {
            System.out.println("Error while deleting brand from the database!");
        }
    }
}
