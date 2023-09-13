import Repository.AuthorRepository;
import Repository.BookRepository;
import model.Author;
import model.Book;
import model.Author;
import service.AuthorService;
import service.BookService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection= DriverManager.getConnection
                ("jdbc:postgresql://localhost:5432/postgres","postgres","abbas1381.aa");
        AuthorRepository authorsRepository=new AuthorRepository(connection);
        BookRepository bookRepository=new BookRepository(connection);
        AuthorService authorService=new AuthorService(authorsRepository);
        BookService bookService=new BookService (bookRepository);

        authorService.register("abbas","hooshangi",3);
        bookService.addBook("tars",1398,1);

        Author author=AuthorRepository.load(1);
        System.out.println("author"+author.getFirstname()+author.getLastname());

        Book book=bookRepository.load(1);
        System.out.println("book"+book.getTitle());

        bookRepository.delete(book);

        connection.close();
    }
}
