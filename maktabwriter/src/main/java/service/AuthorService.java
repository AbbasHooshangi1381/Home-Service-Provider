package service;

import Repository.AuthorRepository;
import model.Author;

import java.sql.SQLException;

public class AuthorService {
    private AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void register(String firstName, String lastName, int age) throws SQLException {
        int authorId = generateAuthorId();
        Author author = new Author(authorId, firstName, lastName, age);
        authorRepository.save(author);
        System.out.println("Author registered successfully.");
    }

    private int generateAuthorId() {
        return 0;
    }
}

