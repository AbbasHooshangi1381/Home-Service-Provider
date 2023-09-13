package model;

public class Author {
    private int AuthorId;
    private String firstname;
    private String lastname;
    private int age;

    public Author(int AuthorId ,String firstname, String lastname, int age) {
        this.AuthorId=AuthorId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;

    }

    public Integer getAuthorid() {
        return AuthorId;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getAge() {
        return age;
    }
}
