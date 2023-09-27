package service;

import connection.JdbcConnection;
import repository.BrandRepository;
import repository.ShareHolderRepository;
import role.Brand;
import role.ShareHolder;

import java.rmi.NotBoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class ShareHolderService {

    private Connection connection= JdbcConnection.getConnection();
    Scanner input = new Scanner(System.in);
    private final ShareHolderRepository shareHolderRepository = new ShareHolderRepository(connection);


    public void registerShareHolder(){
        ShareHolder shareHolder = new ShareHolder("tahere mohammadi","09125489658","0926184587");
        int result = 0;
        try {
            result = shareHolderRepository.save(shareHolder);
        } catch (SQLException e) {
throw new RuntimeException();
        }

        if (result != 0) {
            System.out.println(shareHolder.getName() + "successfully shareHolder added to data base");
        } else
            System.out.println(" wrong! shareHolder");
    }


    public void changeNameShareHolder() throws SQLException {
        System.out.println("put your new name");
        String name=input.nextLine();
        int result=shareHolderRepository.updateNameshareHolder(name);
        if (result != 0) {
            System.out.println( "successfully edited updateNameshareHolder to data base");
        } else
            System.out.println(" Oops! updateNameshareHolder");
    }
    public void changePhoneNumber() throws SQLException {
        System.out.println("put your new phoneNumber");
        String phoneNumber=input.nextLine();
        int result=shareHolderRepository.updatePhoneNumber(phoneNumber);
        if (result != 0) {
            System.out.println( "successfully edited phoneNumber to data base");
        } else
            System.out.println(" Oops! phoneNumber");
    }
    public void changeNationalCode() throws SQLException {
        System.out.println("put your new NationalCode");
        String NationalCode=input.nextLine();
        int result=shareHolderRepository.updatenationalCode(NationalCode);
        if (result != 0) {
            System.out.println( "successfully edited NationalCode to data base");
        } else
            System.out.println(" Oops! NationalCode");
    }
    public void delete() throws SQLException {
        int result=shareHolderRepository.deleteShareHolder( 2);
        if (result !=0){
            System.out.println("successfully deleted");
        }
        else
            System.out.println("not deleted !");
    }



}
