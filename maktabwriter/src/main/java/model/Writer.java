package model;

public class Writer {
    private String firstname;
    private String lastname;
    private Integer age;
    private String books;
    private String username;
    private String password;

    public Writer(String firstname, String lastname, Integer age, String books, String username, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.books = books;
        this.username = username;
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBooks() {
        return books;
    }

    public void setBooks(String books) {
        this.books = books;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
