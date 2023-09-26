package service;

import repository.BrandRepository;
import repository.UserRepository;
import role.Brand;
import role.User;

import java.sql.SQLException;
import java.util.Scanner;

public class BrandService {
    Scanner input = new Scanner(System.in);

    private final BrandRepository brandRepository = new BrandRepository();

    public BrandService() throws SQLException {
    }

    public void registerBrand() throws SQLException {
        Brand brand = new Brand(1, "sadegh", "www.abbas.com", "it is just a joke!");
        int result = brandRepository.save(brand);

        if (result != 0) {
            System.out.println(brand.getName() + "successfully added to data base");
        } else
            System.out.println(" wrong!");
    }




    public void changeName() throws SQLException {
        System.out.println("put your new name");
        String name=input.nextLine();
        int result=brandRepository.updateNameBrand(name);
        if (result != 0) {
            System.out.println( "successfully edited to data base");
        } else
            System.out.println(" Oops!");
    }
    public void changeWebsite() throws SQLException {
        System.out.println("put your new website");
        String website=input.nextLine();
        int result=brandRepository.updateWebsiteBrand(website);
        if (result != 0) {
            System.out.println( "successfully edited website to data base");
        } else
            System.out.println(" Oops! website");
    }
    public void changeEmail() throws SQLException {
        System.out.println("put your new email");
        String description=input.nextLine();
        int result=brandRepository.updateDescriptionBrand(description);
        if (result != 0) {
            System.out.println( "successfully edited description to data base");
        } else
            System.out.println(" Oops! description");
    }
    public void delete() throws SQLException {
        int result=brandRepository.deleteBrand( 2);
        if (result !=0){
            System.out.println("successfully deleted");
        }
        else
            System.out.println("not deleted !");
    }




}
