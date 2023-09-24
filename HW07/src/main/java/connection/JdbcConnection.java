package connection;

import com.sun.jdi.connect.spi.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {

  private Connection connection  = (Connection) DriverManager.getConnection(
           "jdbc:postgresql://localhost:5432/postgres","postgres","abbas1381.aa"
   );



 public JdbcConnection() throws SQLException {
 }
 public Connection getConnection(){
  return connection;
 }
}
