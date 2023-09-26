package service;

import repository.BrandRepository;
import repository.CategoryRepository;
import role.Brand;
import role.Category;

import java.sql.SQLException;
import java.util.Scanner;

public class CategoryService {
    Scanner input = new Scanner(System.in);

    private final CategoryRepository categoryRepository = new CategoryRepository();

    public CategoryService() {
    }

    public void registerCategory() throws SQLException {
        Category category = new Category("zahra", "this is category");
        int result = categoryRepository.save(category);

        if (result != 0) {
            System.out.println(category.getName() + "successfully category added to data base");
        } else
            System.out.println(" wrong! category");
    }

    public void changeNameCategory() throws SQLException {
        System.out.println("put your new name");
        String nameCategory = input.nextLine();
        int result = categoryRepository.updateNameCategory(nameCategory);
        if (result != 0) {
            System.out.println("successfully edited category to data base");
        } else
            System.out.println(" Oops! category");
    }

    public void changeDescription() throws SQLException {
        System.out.println("put your new description");
        String description = input.nextLine();
        int result = categoryRepository.updateDescription(description);
        if (result != 0) {
            System.out.println("successfully edited description to data base");
        } else
            System.out.println(" Oops! description");
    }

    public void delete() throws SQLException {
        int result = categoryRepository.deleteCategory(2);
        if (result != 0) {
            System.out.println("successfully deleted");
        } else
            System.out.println("not deleted !");
    }
}
