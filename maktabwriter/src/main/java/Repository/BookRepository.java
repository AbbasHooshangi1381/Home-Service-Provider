package Repository;

import Connection.Jdbcconnection;
import model.Author;
import model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRepository {
    Jdbcconnection jdbcconnection = new Jdbcconnection();
    Connection connection = jdbcconnection.getConnection();

    public BookRepository() throws SQLException {
    }

    public void save(Book book) throws SQLException {
        String query = "INSERT INTO books (book_id, title, year, author_id) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, book.getBookId());
        statement.setString(2, book.getTitle());
        statement.setInt(3, book.getYear());
        statement.setInt(4, book.getAuthor().getAuthorId());
        statement.executeUpdate();
        System.out.println("Book saved successfully.");


    }

    public Book load(int bookId) throws SQLException {

        String query = "SELECT * FROM books WHERE book_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, bookId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            int id = resultSet.getInt("book_id");
            String title = resultSet.getString("title");
            int year = resultSet.getInt("year");
            int authorId = resultSet.getInt("author_id");
            Author author = new AuthorRepository(connection).load(authorId);
            return new Book(id, title, year, author);


        }
        return null;
    }
    public void delete(Book book) throws SQLException {

        String query = "DELETE FROM books WHERE book_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, book.getBookId());
        statement.executeUpdate();
        System.out.println("Book deleted successfully.");


    }
}