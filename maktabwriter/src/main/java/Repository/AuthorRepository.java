package Repository;
import  Connection.Jdbcconnection;
import model.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuthorRepository {
 Jdbcconnection jdbcconnection=new Jdbcconnection();
 Connection connection=jdbcconnection.getConnection();

 public AuthorRepository() throws SQLException {
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

}
