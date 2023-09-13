package service;

import model.Author;
import repository.AuthorRepository;

import java.sql.SQLException;

public class AuthorService {
 private final AuthorRepository authorRepository=new AuthorRepository();


    public AuthorService() throws SQLException {
    }
    public void register() throws SQLException {
        Author author=new Author(1,"abbas","mohammadi",25);
        int result=authorRepository.save(author);
        if (result!=0){
            System.out.println(author.getFirstname()+"success");


        }
        else
            System.out.println("oops");


}



}
