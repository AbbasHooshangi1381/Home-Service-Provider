package service;

import Repository.AuthorRepository;
import Repository.BookRepository;
import model.Author;
import model.Book;

import java.sql.Connection;
import java.sql.SQLException;


public class BookService {
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(String title, int year, int authorId) throws SQLException {
        Author author = AuthorRepository.load(authorId);
        if (author != null) {
            int bookId = generateBookId();
            Book book = new Book(bookId, title, year, author);
            bookRepository.save(book);
            System.out.println("Book added successfully.");
        } else {
            System.out.println("Author not found.");
        }
    }

    private int generateBookId() {
        return 0;
    }
}

