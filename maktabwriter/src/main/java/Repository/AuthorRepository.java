package Repository;
import  Connection.Jdbcconnection;
import model.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorRepository {
 Jdbcconnection jdbcconnection=new Jdbcconnection();
 Connection connection=jdbcconnection.getConnection();

 public AuthorRepository(Connection connection) throws SQLException {
 }

 public int save(Author author) throws SQLException {
  String add="INSERT INTO authors(author_id,firstname,lastname,age)VALUES(?,?,?,?)";
  PreparedStatement preparedStatement= connection.prepareStatement(add);
  preparedStatement.setInt(1,author.getAuthorid());

  preparedStatement.setString(2,author.getFirstname());
  preparedStatement.setString(3,author.getLastname());
  preparedStatement.setInt(4, author.getAge());

  int result= preparedStatement.executeUpdate();
  return result;


 }

 public Author load(int authorId) throws SQLException {
  String query = "SELECT * FROM authors WHERE author_id = ?";
  PreparedStatement statement = connection.prepareStatement(query);
  statement.setInt(1, authorId);
  ResultSet resultSet = statement.executeQuery();
  if (resultSet.next()) {
   int id = resultSet.getInt("author_id");
   String firstName = resultSet.getString("first_name");
   String lastName = resultSet.getString("last_name");
   int age = resultSet.getInt("age");
    new Author(id, firstName, lastName, age);

  }

  return null;
 }
}