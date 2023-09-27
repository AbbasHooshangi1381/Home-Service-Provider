package service;

import connection.JdbcConnection;
import repository.BrandRepository;
import repository.CategoryRepository;
import role.Category;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class CategoryService {
    Scanner input = new Scanner(System.in);
    private Connection connection = JdbcConnection.getConnection();
    private final CategoryRepository categoryRepository = new CategoryRepository(connection);


    public void registerCategory(Category category) throws SQLException {
        int result = categoryRepository.save(connection, category);

        if (result != 0) {
            System.out.println(category.getName() + " successfully added to the database");
        } else {
            System.out.println("Something went wrong while saving the category");
        }
    }

    public void changeNameCategory() throws SQLException {
        System.out.println("Enter the new name for the category");
        String nameCategory = input.nextLine();
        int result = categoryRepository.updateNameCategory(connection, nameCategory);

        if (result != 0) {
            System.out.println("Successfully updated the category name in the database");
        } else {
            System.out.println("Something went wrong while updating the category name");
        }
    }

    public void changeDescription() throws SQLException {
        System.out.println("Enter the new description for the category");
        String description = input.nextLine();
        int result = categoryRepository.updateDescription(connection, description);

        if (result != 0) {
            System.out.println("Successfully updated the category description in the database");
        } else {
            System.out.println("Something went wrong while updating the category description");
        }
    }

    public void delete() throws SQLException {
        int result = categoryRepository.deleteCategory(connection, 2);

        if (result != 0) {
            System.out.println("Successfully deleted the category from the database");
        } else {
            System.out.println("Something went wrong while deleting the category");
        }
    }
}